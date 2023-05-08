/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObtenerExamenesSolicitados {
    
    public static List<SolicitudExamen> solicitudesExamenFinalizados(int idPaciente){
        Connection conexion = Conexion.getConnection();
        List<SolicitudExamen> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idsolicitud, idlaboratorio,fecha_finalizado FROM solicitud_examen WHERE idpaciente = ? "
                + "AND estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idPaciente);
            ps.setString(2, "FINALIZADA");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idSolicitud = rs.getInt("idsolicitud");
                int idlaboratorio = rs.getInt("idlaboratorio");
                String fecha = rs.getString("fecha_finalizado");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                List<ExamenSolicitado> examenes = examenesFinalizados(idSolicitud);
                
                SolicitudExamen solicitud = new SolicitudExamen(idSolicitud, idlaboratorio, fecha, examenes);
                
                solicitudes.add(solicitud);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return solicitudes;
    }
    
    public static List<ExamenSolicitado> examenesFinalizados(int idSolicitud){
        Connection conexion = Conexion.getConnection();
        List<ExamenSolicitado> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idexamenes_solicitados,idexamen FROM examenes_solicitados WHERE idsolicitud = ? ";
        
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
    
    public static byte[] obtenerArchivo(int idExamen){
        Connection conexion = Conexion.getConnection();
        byte[] archivo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT resultado FROM examenes_solicitados WHERE idexamenes_solicitados = ? ";
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idExamen);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Blob blob = rs.getBlob("resultado");
                archivo = blob.getBytes(1, (int)blob.length());
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return archivo;
    }
    
}
