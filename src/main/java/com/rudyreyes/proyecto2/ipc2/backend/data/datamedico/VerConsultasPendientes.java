/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.ObtenerPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.FechaConsulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VerConsultasPendientes {
    
    public static List<Consulta> obtenerConsultas(int idMedico){
        Connection conexion = Conexion.getConnection();
        List<Consulta> consultas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta, idpaciente, idespecialidad FROM consulta WHERE idmedico = ? "
                + "AND estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setString(2, "AGENDADA");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idConsulta = rs.getInt("idconsulta");
                int idPaciente = rs.getInt("idpaciente");
                int idEspecialidad = rs.getInt("idespecialidad");
                
                //NECESITAMOS OBTENER LA FECHA AGENDADA
                FechaConsulta fecha = fechaAgendada(idConsulta);
                String nombrePaciente = ObtenerPaciente.obtenerNombre(idPaciente);
                Consulta con = new Consulta(idConsulta, idPaciente, idEspecialidad, fecha, nombrePaciente);
                
                consultas.add(con);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return consultas;
    }
    
    
    public static FechaConsulta fechaAgendada(int idConsulta){
        Connection conexion = Conexion.getConnection();
        FechaConsulta fechaAgendada = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT fecha, hora_inicial, hora_final FROM fecha_agendada WHERE idconsulta = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idConsulta);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String fecha = rs.getString("fecha");
                String horaInicial = rs.getString("hora_inicial");
                String horaFinal = rs.getString("hora_final");
                
                fechaAgendada = new FechaConsulta(fecha, horaInicial, horaFinal);
                
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return fechaAgendada;
    }
    
    public static void finalizarConsulta(Consulta consulta){
        
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE consulta set informe=?, estado=? WHERE idconsulta=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, consulta.getInforme());
            ps.setString(2,"FINALIZADA");
            ps.setInt(3, consulta.getIdConsulta());
            
            int resultado = ps.executeUpdate();
            
            //MANDO A CAMBIAR EL HORARIO
            cambiarEstadoHorario(consulta);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void cambiarEstadoHorario(Consulta consulta){
        
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE fecha_agendada set estado=? WHERE idconsulta=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1,"FINALIZADA");
            ps.setInt(2, consulta.getIdConsulta());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //AHORA NECESITO AGREGAR LOS EXAMENES
    public static void agregarExamenesConsulta(List<ExamenSolicitado> examenes){
        
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE consulta set estado=? WHERE idconsulta=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1,"EXAMEN_PENDIENTE");
            ps.setInt(2, examenes.get(0).getIdConsulta());
            
            int resultado = ps.executeUpdate();
            
            for(ExamenSolicitado ex: examenes){
                agregarExamenConsulta(ex);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    private static void agregarExamenConsulta(ExamenSolicitado ex) {
         PreparedStatement ps = null;
        String query = "INSERT INTO examen_consulta(idconsulta, idexamen) "
                + "VALUES(?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, ex.getIdConsulta());
            ps.setInt(2, ex.getIdExamen());
            ps.executeUpdate();
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
}
