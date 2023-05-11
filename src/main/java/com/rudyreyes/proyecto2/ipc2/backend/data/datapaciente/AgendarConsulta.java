/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.ActualizarPorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.data.AgregarSaldoAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.AgregarEspecialidad;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.AgregarSaldoMedico;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RegistrarEspecialidad;
import static com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RegistrarEspecialidad.obtenerAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.FechaConsulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.HorarioMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Medico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AgendarConsulta {
    
    //1. NECESITAMOS OBTENER TODOS LOS MEDICOS CON 
    //      iDMEDICO, NOMBRE, ESPECIALIDAD, PRECIO
    //2. NECESITAMOS OBTENER TODOS LOS HORARIOS DISPONIBLES
    
    
    //AQUI VAMOS A OBTENER EL  MEDICO con ESPECIALIDADES
    public static List<Medico> obtenerMedicos(){
        Connection conexion = Conexion.getConnection();
        List<Medico> medicos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idmedico, nombre,usuario, saldo FROM medico";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idMedico = rs.getInt("idmedico");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("usuario");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                //NECESITAMOS OBTENER LAS ESPECIALIDADES
                List<EspecialidadDelMedico> especialidades = AgregarEspecialidad.getEspecialidadesMedico(idMedico);
                
                Medico medico = new Medico(idMedico, nombre, nombreUsuario, saldo, especialidades);
                
                medicos.add(medico);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return medicos;
    }
    
    //AQUI DEBO RETORNAR LAS FECHAS DEL MEDICO DISPONIBLES PARA LA CONSULTA
    public static List<HorarioMedico> obtenerHorarios(FechaConsulta fecha){
        Connection conexion = Conexion.getConnection();
        List<HorarioMedico> horarios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idhorario, hora_inicial, hora_final FROM horario_atencion"
                + " WHERE idmedico = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, fecha.getIdMedico());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idHorario = rs.getInt("idhorario");
                String horaInicial = rs.getString("hora_inicial");
                String horaFinal = rs.getString("hora_final");
                
                List<HorarioMedico> horas = separarHoras(horaInicial, horaFinal);
                for(HorarioMedico hr: horas){
                    if(verificarHora(fecha, hr.getHoraInicial(), hr.getHoraFinal(), idHorario)){
                        horarios.add(new HorarioMedico(hr.getHoraInicial(), hr.getHoraFinal(),fecha.getIdMedico(), idHorario));
                    }
                }
                
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return horarios;
    }
    
    public static List<HorarioMedico> separarHoras(String horaInicial, String horaFinal) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    LocalTime inicio = LocalTime.parse(horaInicial, formatter);
    LocalTime fin = LocalTime.parse(horaFinal, formatter);

    List<HorarioMedico> horas = new ArrayList<>();

    while (inicio.isBefore(fin)) {
      LocalTime siguiente = inicio.plusHours(1);
      HorarioMedico intervalo = new HorarioMedico(inicio.format(formatter), siguiente.format(formatter));
      horas.add(intervalo);
      inicio = siguiente;
    }

    return horas;
  }
    
    private static boolean verificarHora(FechaConsulta fecha, String horaInicial, String horaFinal, int idHorario){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idConsulta = 0;
        
        String query = "SELECT  idconsulta FROM fecha_agendada "
                + " WHERE idhorario = ? AND fecha = ? AND hora_inicial = ? AND hora_final = ? AND estado = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idHorario);
            ps.setString(2, fecha.getFecha());
            ps.setString(3, horaInicial);
            ps.setString(4, horaFinal);
            ps.setString(5, "AGENDADA");

            
            rs = ps.executeQuery();
            
            while(rs.next()){
                idConsulta = rs.getInt("idconsulta");
                System.out.println(idConsulta);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        if(idConsulta == 0){
            return true;
            //SI ES TRUE EL HORARIO ESTA LIBRE
        }else{
            return false;
        }
        
        
    }
    
    
    //AHORA DEBO AGREGAR LA CONSULTA
    public static void agendarConsulta(Consulta consulta){
        
        
        PorcentajeCobro porcentaje = ActualizarPorcentajeCobro.obtenerPorcentaje();
        String fechaActual = Utilidades.obtenerFechaActual();
        PreparedStatement ps = null;
        String query = "INSERT INTO consulta(idpaciente, idmedico, idespecialidad, porcentaje_app, "
                + "fecha_creacion, precio, estado) VALUES(?,?,?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, consulta.getIdPaciente());
            ps.setInt(2, consulta.getIdMedico());
            ps.setInt(3, consulta.getIdEspecialidad());
            ps.setDouble(4, porcentaje.getPorcentaje());
            ps.setString(5, fechaActual);
            ps.setBigDecimal(6, consulta.getPrecio());
            ps.setString(7, "AGENDADA");
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int idConsulta=0;
            while(rs.next()){
                idConsulta = rs.getInt(1);
                System.out.println(idConsulta);
            }
            
            //AHORA NECESITO MODIFICAR LA FECHA DE LA CONSULTA Y SUBIR LA GANANCIA AL ADMIN Y AL MEDICO
            //int idConsulta = obtenerIDConsulta();
            consulta.getFechaAgendada().setIdConsulta(idConsulta);
            AgendarFechaConsulta.agendarFecha(consulta.getFechaAgendada());
            
            //AHORA TOCA LAS GANANCIAS
            BigDecimal saldoAdmin = consulta.getPrecio().multiply(BigDecimal.valueOf(porcentaje.getPorcentaje()));
            System.out.println("saldo admin" + saldoAdmin);
            AgregarSaldoAdmin.sumarSaldo(saldoAdmin);
            //AHORA TOCA MEDICO
            BigDecimal saldoMedico = consulta.getPrecio().subtract(saldoAdmin);
            System.out.println("saldo medico" +saldoMedico);
            AgregarSaldoMedico.sumarSaldo(saldoMedico, consulta.getIdMedico());
            //AHORA TOCA EL PACIENTE
            AgregarSaldoPaciente.restarSaldo(consulta.getPrecio(), consulta.getIdPaciente());
        }catch(Exception e){
            System.err.println(e);
        }
    }

//OBTENER EL ID DE LA CONSULTA
    public static int obtenerIDConsulta(){
        
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        int idConsulta = 0;
        ResultSet rs = null;
        String query = "SELECT  idconsulta FROM consulta ORDER BY idconsulta ASC";
        
        try {
            
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                idConsulta = rs.getInt("idconsulta");
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return idConsulta;
    }
    
}
