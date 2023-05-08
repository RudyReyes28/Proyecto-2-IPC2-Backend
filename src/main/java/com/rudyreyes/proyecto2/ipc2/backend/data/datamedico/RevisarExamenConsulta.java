/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudy-reyes
 */
public class RevisarExamenConsulta {
    
    public static List<Consulta> revisarConsultas(int idMedico){
        Connection conexion = Conexion.getConnection();
        List<Consulta> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta, idpaciente FROM consulta WHERE idmedico = ? "
                + "AND estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setString(2, "PENDIENTE_REVISION");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idConsulta = rs.getInt("idconsulta");
                int idPaciente = rs.getInt("idpaciente");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                List<ExamenSolicitado> examenes = examenesConsulta(idConsulta);
                
                Consulta solicitud = new Consulta(idConsulta, idPaciente, idMedico, examenes);
                
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
        String query = "SELECT idexamen_consulta,idexamen FROM examen_consulta WHERE idconsulta = ? ";
        
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
    
    public static byte[] obtenerArchivo(int idExamen){
        Connection conexion = Conexion.getConnection();
        byte[] archivo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT resultado FROM examen_consulta WHERE idexamen_consulta = ? ";
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
