
package com.rudyreyes.proyecto2.ipc2.backend.data.datalab;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SolicitudExamenesPendientes {
    
    public static List<SolicitudExamen> solicitudesPendientes(int idLaboratorio){
        Connection conexion = Conexion.getConnection();
        List<SolicitudExamen> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idsolicitud, idpaciente,fecha_solicitado FROM solicitud_examen WHERE idlaboratorio = ? "
                + "AND estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idLaboratorio);
            ps.setString(2, "PENDIENTE");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idSolicitud = rs.getInt("idsolicitud");
                int idPaciente = rs.getInt("idpaciente");
                String fecha = rs.getString("fecha_solicitado");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                List<ExamenSolicitado> examenes = examenesSolicitados(idSolicitud);
                
                SolicitudExamen solicitud = new SolicitudExamen(idSolicitud, idPaciente, fecha, examenes);
                
                solicitudes.add(solicitud);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return solicitudes;
    }
    
    public static List<ExamenSolicitado> examenesSolicitados(int idSolicitud){
        Connection conexion = Conexion.getConnection();
        List<ExamenSolicitado> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idexamenes_solicitados,idexamen FROM examenes_solicitados WHERE idsolicitud = ? "
                + "AND resultado IS NULL";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idSolicitud);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idExamenSolicitado = rs.getInt("idexamenes_solicitados");
                int idExamen = rs.getInt("idexamen");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                ExamenSolicitado ex = new ExamenSolicitado(idExamenSolicitado,idSolicitud, idExamen);
                
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
        
        String query = "UPDATE examenes_solicitados SET resultado =  ? WHERE idexamenes_solicitados=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBinaryStream(1, inputStream, examen.length);
            ps.setInt(2, idExamenSolicitado);
            
            int resultado = ps.executeUpdate();
            
            if(verificarExamenesSolicitados(idExamenSolicitado)){
                finalizarSolicitud(idExamenSolicitado);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //necesito obtener la consulta y verificar si hay mas nulos en esos examenes
    //si no hay, cambiar el estado y agregar fecha
    
    private static boolean verificarExamenesSolicitados(int idExamen){
        int resultado = 0;
        int idSolicitud = obtenerSolicitud(idExamen);
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idexamenes_solicitados FROM examenes_solicitados WHERE idsolicitud = ? "
                + "AND resultado IS NULL";
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idSolicitud);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                 resultado = rs.getInt("idexamenes_solicitados");
                
                
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
    
    
    private static int obtenerSolicitud(int idExamen){
        Connection conexion = Conexion.getConnection();
        int idSolicitud = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idsolicitud FROM examenes_solicitados WHERE idexamenes_solicitados = ? ";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idExamen);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                 idSolicitud = rs.getInt("idsolicitud");
                
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return idSolicitud;
    }
    
    public static void finalizarSolicitud(int idExamen){
        int idSolicitud = obtenerSolicitud(idExamen);
        String fecha= Utilidades.obtenerFechaActual();
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE solicitud_examen SET fecha_finalizado =  ?, estado=? WHERE idsolicitud=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, fecha);
            ps.setString(2, "FINALIZADA");
            ps.setInt(3, idSolicitud);
            
            int resultado = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
