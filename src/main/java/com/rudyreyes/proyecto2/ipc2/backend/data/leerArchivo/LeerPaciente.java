/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.leerArchivo;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeerPaciente {
    
    public static void registrarPaciente(Usuario usuario){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO paciente(idpaciente, tipo_usuario, nombre, usuario, contraseña, direccion, cui, "
                + "telefono, correo, fecha_nacimiento, saldo) values (?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getTipoUsuario());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContraseña());
            ps.setString(6, usuario.getDireccion());
            ps.setInt(7, usuario.getCUI());
            ps.setInt(8, usuario.getTelefono());
            ps.setString(9, usuario.getCorreo());
            ps.setString(10, usuario.getFechaNacimiento());
            ps.setBigDecimal(11, usuario.getSaldo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void agendarConsulta(Consulta consulta){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO consulta(idconsulta, idpaciente, idmedico, idespecialidad, porcentaje_app, "
                + "fecha_creacion, precio, informe, estado) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, consulta.getIdConsulta());
            ps.setInt(2, consulta.getIdPaciente());
            ps.setInt(3, consulta.getIdMedico());
            ps.setInt(4, consulta.getIdEspecialidad());
            ps.setDouble(5, consulta.getPorcentajeApp());
            ps.setString(6, consulta.getFechaCreacion());
            ps.setBigDecimal(7, consulta.getPrecio());
            ps.setString(8, consulta.getInforme());
            ps.setString(9, consulta.getEstado());
            ps.executeUpdate();
            
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void agendarFechaConsulta(int idConsulta,String fecha, String horaInicial, String horaFinal,String estado,int idMedico){
        if(!estado.equals("FINALIZADA")){
            estado = "AGENDADA";
        }
        
        
        int idHorario = obtenerIdHorario(idMedico, horaInicial);
        
        PreparedStatement ps = null;
        String query = "INSERT INTO fecha_agendada(idconsulta, fecha, idhorario, hora_inicial, "
                + "hora_final, estado) VALUES(?,?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idConsulta);
            ps.setString(2, fecha);
            ps.setInt(3, idHorario);
            ps.setString(4, horaInicial);
            ps.setString(5, horaFinal);
            ps.setString(6, estado);
            ps.executeUpdate();
            
            //AHORA NECESITO MODIFICAR LA FECHA DE LA CONSULTA Y SUBIR LA GANANCIA AL ADMIN Y AL MEDICO
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void agendarExamenes(int idConsulta, int idExamen){
        PreparedStatement ps = null;
        String query = "INSERT INTO examen_consulta(idconsulta, idexamen) "
                + "VALUES(?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idConsulta);
            ps.setInt(2, idExamen);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static int obtenerIdHorario(int idMedico, String hora){
        Connection conexion = Conexion.getConnection();
        int id=0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idhorario FROM horario_atencion WHERE idmedico = ? AND ? BETWEEN hora_inicial AND hora_final";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setString(2, hora);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("idhorario");
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return id;
    }
    
}
