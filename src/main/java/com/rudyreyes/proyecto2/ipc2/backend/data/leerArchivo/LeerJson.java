/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.leerArchivo;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Especialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author rudy-reyes
 */
public class LeerJson {
    
    public static void leerTodo(String nombreArchivo){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("/tmp/"+nombreArchivo)) {
            Object obj = jsonParser.parse(reader);

            leerAdministrador((JSONObject) obj);
            cargarEspecialidad((JSONObject) obj);
            cargarTiposExamen((JSONObject) obj);
            leerMedico((JSONObject) obj);
            leerLaboratorio((JSONObject) obj);
            leerPacientes((JSONObject) obj);
            leerConsulta((JSONObject) obj);
            leerSolicitudes((JSONObject) obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public static void leerAdministrador(JSONObject jsonObject) {
        //JSONArray admins = (JSONArray) jsonObject.get("admin");

        //for (Object admin : admins) {
        try{
            JSONObject ad = (JSONObject) jsonObject.get("admin");

            int idAdmin = Integer.parseInt(String.valueOf(ad.get("id")));
            String tipoUsuario = "ADMINISTRADOR";
            String nombre = String.valueOf(ad.get("nombre"));
            String usuario = String.valueOf(ad.get("username"));
            String contraseña = String.valueOf(ad.get("password"));
            String email = String.valueOf(ad.get("email"));
            String fechaNacimiento = String.valueOf(ad.get("fecha_nacimiento"));
            BigDecimal saldo = BigDecimal.valueOf(Double.parseDouble(String.valueOf(ad.get("saldo"))));;

            
            Usuario usser = new Usuario(idAdmin, tipoUsuario, nombre, usuario, contraseña, email, fechaNacimiento, saldo);
            
            LeerAdministrador.registrarAdmin(usser);
            LeerAdministrador.agregarPorcentaje(idAdmin);
        }catch(Exception e){
            e.printStackTrace();
        }    
        //}

    }
    
    public static void cargarEspecialidad(JSONObject jsonObject) {
        JSONArray especialidades = (JSONArray) jsonObject.get("especialidades");

        for (Object especialidad : especialidades) {
            JSONObject espe = (JSONObject) especialidad;
            try {
                int idEspecialidad = Integer.parseInt(String.valueOf(espe.get("id")));
                String nombre = String.valueOf(espe.get("nombre"));
                String descripcion = String.valueOf(espe.get("descripcion"));

                Especialidad nuevaEspe = new Especialidad(idEspecialidad, nombre, descripcion);

                LeerMedico.nuevaEspecialidad(nuevaEspe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void cargarTiposExamen(JSONObject jsonObject) {
        JSONArray tipoExamenes = (JSONArray) jsonObject.get("tipos_examenes");

        for (Object examen : tipoExamenes) {
            JSONObject tipo = (JSONObject) examen;
            
            try {
                int idTipo = Integer.parseInt(String.valueOf(tipo.get("id")));
                String nombre = String.valueOf(tipo.get("nombre"));
                String descripcion = String.valueOf(tipo.get("descripcion"));

                TipoExamen tipoE = new TipoExamen(idTipo, nombre, descripcion);

                LeerLaboratorio.nuevoTipoExamen(tipoE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void leerMedico(JSONObject jsonObject) {
        JSONArray medicos = (JSONArray) jsonObject.get("medicos");

        for (Object medico : medicos) {
            JSONObject ad = (JSONObject) medico;
            
            try {
                int idMedico = Integer.parseInt(String.valueOf(ad.get("id")));
                String tipoUsuario = "MEDICO";
                String nombre = String.valueOf(ad.get("nombre"));
                String usuario = String.valueOf(ad.get("username"));
                String contraseña = String.valueOf(ad.get("password"));
                String direccion = String.valueOf(ad.get("direccion"));

                String cuiString = String.valueOf(ad.get("cui"));
                
                int cui = Utilidades.reducirCui(cuiString);

                int telefono = Integer.parseInt(String.valueOf(ad.get("telefono")));
                String email = String.valueOf(ad.get("email"));
                String fechaNacimiento = String.valueOf(ad.get("fecha_nacimiento"));
                BigDecimal saldo = BigDecimal.valueOf(Double.parseDouble(String.valueOf(ad.get("saldo"))));;

                Usuario nuevoMedico = new Usuario(idMedico, tipoUsuario, nombre, usuario, contraseña, direccion, cui, telefono, email, fechaNacimiento, saldo);
                LeerMedico.registrarMedico(nuevoMedico);

                //PARA ESCRIBIR LOS HORARIOS
                JSONArray horarios = (JSONArray) ad.get("horarios");
                for (Object horario : horarios) {
                    //JSONObject h = (JSONObject) horario;

                    String hora = String.valueOf(horario);
                    String horaArreglo[] = Utilidades.separarHoras(hora);
                    String horaInicial = horaArreglo[0];
                    String horaFinal = horaArreglo[1];

                    LeerMedico.registrarHorario(idMedico, horaInicial, horaFinal);

                }

                //PARA ESCRIBIR LAS ESPECIALIDADES
                JSONArray especialidades = (JSONArray) ad.get("especialidades");
                for (Object espe : especialidades) {
                    JSONObject especialidad = (JSONObject) espe;

                    int idEspecialidad = Integer.parseInt(String.valueOf(especialidad.get("id")));
                    BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(String.valueOf(especialidad.get("precio"))));

                    LeerMedico.registrarEspecialidad(idMedico, idEspecialidad, precio);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    
    public static void leerLaboratorio(JSONObject jsonObject) {
        JSONArray laboratorios = (JSONArray) jsonObject.get("laboratorios");

        for (Object laboratorio : laboratorios) {
            JSONObject lab = (JSONObject) laboratorio;

            try {
                int idLaboratorio = Integer.parseInt(String.valueOf(lab.get("id")));
                String tipoUsuario = "LABORATORIO";
                String nombre = String.valueOf(lab.get("nombre"));
                String usuario = String.valueOf(lab.get("username"));
                String contraseña = String.valueOf(lab.get("password"));
                String direccion = String.valueOf(lab.get("direccion"));

                String cuiString = String.valueOf(lab.get("cui"));
                
                int cui = Utilidades.reducirCui(cuiString);
                int telefono = Integer.parseInt(String.valueOf(lab.get("telefono")));
                String email = String.valueOf(lab.get("email"));
                String fechaNacimiento = String.valueOf(lab.get("fecha_fundacion"));
                BigDecimal saldo = BigDecimal.valueOf(Double.parseDouble(String.valueOf(lab.get("saldo"))));;

                Usuario nuevoLab = new Usuario(idLaboratorio, tipoUsuario, nombre, usuario, contraseña, direccion, cui, telefono, email, fechaNacimiento, saldo);
                LeerLaboratorio.registrarLaboratorio(nuevoLab);

                //PARA ESCRIBIR LOS EXAMENES
                JSONArray examenes = (JSONArray) lab.get("examenes");
                for (Object examen : examenes) {
                    JSONObject ex = (JSONObject) examen;

                    int idExamen = Integer.parseInt(String.valueOf(ex.get("id")));
                    BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(String.valueOf(ex.get("precio"))));

                    LeerLaboratorio.registrarTipoExamen(idLaboratorio, idExamen, precio);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    
    public static void leerPacientes(JSONObject jsonObject) {
        JSONArray pacientes = (JSONArray) jsonObject.get("pacientes");

        for (Object paciente : pacientes) {
            JSONObject lab = (JSONObject) paciente;

            try {
                int idPaciente = Integer.parseInt(String.valueOf(lab.get("id")));
                String tipoUsuario = "PACIENTE";
                String nombre = String.valueOf(lab.get("nombre"));
                String usuario = String.valueOf(lab.get("username"));
                String contraseña = String.valueOf(lab.get("password"));
                String direccion = String.valueOf(lab.get("direccion"));
                String cuiString = String.valueOf(lab.get("cui"));
                
                int cui = Utilidades.reducirCui(cuiString);
                int telefono = Integer.parseInt(String.valueOf(lab.get("telefono")));
                String email = String.valueOf(lab.get("email"));
                String fechaNacimiento = String.valueOf(lab.get("fecha_nacimiento"));
                BigDecimal saldo = BigDecimal.valueOf(Double.parseDouble(String.valueOf(lab.get("saldo"))));;

                Usuario nuevoPaciente = new Usuario(idPaciente, tipoUsuario, nombre, usuario, contraseña, direccion, cui, telefono, email, fechaNacimiento, saldo);

                LeerPaciente.registrarPaciente(nuevoPaciente);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    
    public static void leerConsulta(JSONObject jsonObject) {
        JSONArray consultas = (JSONArray) jsonObject.get("consultas");

        for (Object consulta : consultas) {
            JSONObject lab = (JSONObject) consulta;

            try {
                int idConsulta = Integer.parseInt(String.valueOf(lab.get("id")));
                int idPaciente = Integer.parseInt(String.valueOf(lab.get("paciente")));
                int idMedico = Integer.parseInt(String.valueOf(lab.get("médico")));
                int idEspecialidad = Integer.parseInt(String.valueOf(lab.get("especialidad")));
                double porcentaje = Double.parseDouble(String.valueOf(lab.get("porcentaje_aplicacion")));
                String fechaCreacion = String.valueOf(lab.get("fecha_creacion"));
                BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(String.valueOf(lab.get("precio"))));;
                
                String informe = "";
                
                String estado = String.valueOf(lab.get("estado"));
                
                if(estado.equals("FINALIZADA")){
                    informe = String.valueOf(lab.get("informe_finalizacion"));
                }

                Consulta nuevaConsulta = new Consulta(idConsulta, idPaciente, idMedico, idEspecialidad, porcentaje, fechaCreacion, precio, informe, estado);
                LeerPaciente.agendarConsulta(nuevaConsulta);
                
                Utilidades.repartirGanancias(precio, porcentaje, idMedico);

                //PARA ASIGNAR LAS FECHAS
                String fechaAgendada = String.valueOf(lab.get("fecha_agendada"));
                String fechaHora[] = Utilidades.separarFechaHora(fechaAgendada);
                String fecha = fechaHora[0];
                String horaInicial = fechaHora[1];
                String horaFinal = Utilidades.obtenerHoraFinal(horaInicial);
                LeerPaciente.agendarFechaConsulta(idConsulta, fecha, horaInicial, horaFinal, estado, idMedico);

                if (estado.equals("EXAMEN_PENDIENTE")) {
                    //PARA ESCRIBIR LOS EXAMENES
                    JSONArray examenes = (JSONArray) lab.get("examenes_solicitados");
                    for (Object examen : examenes) {
                        JSONObject ex = (JSONObject) examen;

                        int idExamen = Integer.parseInt(String.valueOf(ex.get("id")));

                        LeerPaciente.agendarExamenes(idConsulta, idExamen);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    
    public static void leerSolicitudes(JSONObject jsonObject) {
        JSONArray solicitudes = (JSONArray) jsonObject.get("solicitudes");

        for (Object solicitud : solicitudes) {
            JSONObject lab = (JSONObject) solicitud;

            try {
                int idSolicitud = Integer.parseInt(String.valueOf(lab.get("id")));
                int idPaciente = Integer.parseInt(String.valueOf(lab.get("paciente")));
                int idLaboratorio = Integer.parseInt(String.valueOf(lab.get("laboratorio")));
                double porcentaje = Double.parseDouble(String.valueOf(lab.get("porcentaje_aplicacion")));
                String fechaSolicitado = String.valueOf(lab.get("fecha_solicitado"));
                String estado = String.valueOf(lab.get("estado_solicitud"));
                

                SolicitudExamen nuevaSolicitud = new SolicitudExamen(idSolicitud, idPaciente, idLaboratorio, porcentaje, fechaSolicitado, estado);
                LeerLaboratorio.solicitarExamenes(nuevaSolicitud);
                //PARA ESCRIBIR LOS EXAMENES
                BigDecimal total = BigDecimal.ZERO;
                JSONArray examenes = (JSONArray) lab.get("examenes");
                for (Object examen : examenes) {
                    JSONObject ex = (JSONObject) examen;

                    int idExamen = Integer.parseInt(String.valueOf(ex.get("id")));
                    BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(String.valueOf(ex.get("precio"))));;
                    LeerLaboratorio.agregarExamenes(idSolicitud, idExamen, precio);

                    total = total.add(precio);
                }
                
                Utilidades.repartirGananciasLab(total, porcentaje, idLaboratorio);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
