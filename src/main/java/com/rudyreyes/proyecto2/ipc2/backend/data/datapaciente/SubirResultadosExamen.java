/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudy-reyes
 */
public class SubirResultadosExamen {
    
    public static List<Consulta> obtenerConsultasConExamenes(int idPaciente){
        Connection conexion = Conexion.getConnection();
        List<Consulta> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta, idmedico FROM consulta WHERE idpaciente = ? "
                + "AND estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idPaciente);
            ps.setString(2, "EXAMEN_PENDIENTE");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idConsulta = rs.getInt("idconsulta");
                int idMedico = rs.getInt("idmedico");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                List<ExamenSolicitado> examenes = examenesConsulta(idConsulta);
                
                Consulta solicitud = new Consulta(idConsulta, idMedico, examenes);
                
                solicitudes.add(solicitud);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return solicitudes;
    }
    
    public static List<ExamenSolicitado> examenesConsulta(int idConsulta){
        Connection conexion = Conexion.getConnection();
        List<ExamenSolicitado> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idexamen_consulta,idexamen FROM examen_consulta WHERE idconsulta = ? "
                + "AND resultado IS NULL";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idConsulta);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idExamenSolicitado = rs.getInt("idexamen_consulta");
                int idExamen = rs.getInt("idexamen");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                ExamenSolicitado ex = new ExamenSolicitado(idExamenSolicitado,idConsulta, idExamen);
                
                solicitudes.add(ex);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return solicitudes;
    }
    
    public static void guardarExamen(byte[] examen, int idExamenSolicitado){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(examen);
        
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE examen_consulta SET resultado =  ? WHERE idexamen_consulta=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBinaryStream(1, inputStream, examen.length);
            ps.setInt(2, idExamenSolicitado);
            
            int resultado = ps.executeUpdate();
            
            if(verificarExamenesSolicitados(idExamenSolicitado)){
                finalizarConsulta(idExamenSolicitado);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean verificarExamenesSolicitados(int idExamen){
        int resultado = 0;
        int idSolicitud = obtenerConsulta(idExamen);
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idexamen_consulta FROM examen_consulta WHERE idconsulta = ? "
                + "AND resultado IS NULL";
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idSolicitud);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                 resultado = rs.getInt("idexamen_consulta");
                
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        if(resultado == 0){
            //YA NO HAY RESULTADOS NULOS
            return true;
        }else{
            return false;
        }
        
    }
    
    private static int obtenerConsulta(int idExamen){
        Connection conexion = Conexion.getConnection();
        int idSolicitud = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta FROM examen_consulta WHERE idexamen_consulta = ? ";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idExamen);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                 idSolicitud = rs.getInt("idconsulta");
                
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return idSolicitud;
    }
    
    public static void finalizarConsulta(int idExamen){
        int idSolicitud = obtenerConsulta(idExamen);
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE consulta SET estado=? WHERE idconsulta=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, "PENDIENTE_REVISION");
            ps.setInt(2, idSolicitud);
            
            int resultado = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
