/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.reportes;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RevisarExamenConsulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.RecargaPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.ConsultaReportePaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.ExamenesReportePaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.HistorialMedicoReportePaciente;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportesPaciente {
    
    
    /*
    SELECT idrecarga, fecha, monto FROM recarga WHERE idpaciente = 4;
    
    SELECT idconsulta, idmedico, precio, informe FROM consulta WHERE idpaciente = 3 
AND fecha_creacion BETWEEN '2023-03-03' AND '2023-05-10' AND estado = 'FINALIZADA' AND idespecialidad = 2;
    
    SELECT se.idsolicitud, se.idlaboratorio,es.idexamenes_solicitados, es.idexamen, es.precio 
FROM solicitud_examen se 
INNER JOIN examenes_solicitados es ON se.idsolicitud = es.idsolicitud
WHERE se.idpaciente = 3 
AND se.fecha_solicitado BETWEEN '2023-03-03' AND '2023-05-10' AND estado = 'FINALIZADA' AND es.idexamen = 1;
    
    */
    
    public static List<RecargaPaciente> reporteRecargas(RecargaPaciente paciente){
        
         List<RecargaPaciente> recargas = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idrecarga, fecha, monto FROM recarga WHERE idpaciente = ?;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, paciente.getIdPaciente());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idRecarga = rs.getInt("idrecarga");
                String fecha = rs.getString("fecha");                
                BigDecimal monto = rs.getBigDecimal("monto");
                
                RecargaPaciente recarga = new RecargaPaciente(idRecarga, fecha, monto);
                
                recargas.add(recarga);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return recargas;
        
    }
    
    public static List<ConsultaReportePaciente> reporteConsultasP(ConsultaReportePaciente paciente){
        
         List<ConsultaReportePaciente> consultas = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta, idmedico, precio, informe FROM consulta WHERE idpaciente = ? "
                + "AND fecha_creacion BETWEEN ? AND ? "
                + "AND estado = ? AND idespecialidad = ?;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, paciente.getIdPaciente());
            ps.setString(2, paciente.getFechaInicial());
            ps.setString(3, paciente.getFechaFinal());
            ps.setString(4, "FINALIZADA");
            ps.setInt(5, paciente.getIdEspecialidad());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idConsulta = rs.getInt("idconsulta");
                int idMedico = rs.getInt("idconsulta");               
                BigDecimal monto = rs.getBigDecimal("precio");
                String informe = rs.getString("informe"); 
                
                ConsultaReportePaciente cs = new ConsultaReportePaciente(idConsulta, idMedico, monto, informe);
                
                consultas.add(cs);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return consultas;
        
    }
    
    public static List<ExamenesReportePaciente> reporteExamenesP(ExamenesReportePaciente paciente){
        
         List<ExamenesReportePaciente> examenes = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT se.idsolicitud, se.idlaboratorio,es.idexamenes_solicitados, "
                + "es.precio FROM solicitud_examen se INNER JOIN examenes_solicitados es "
                + "ON se.idsolicitud = es.idsolicitud WHERE se.idpaciente = ? "
                + "AND se.fecha_solicitado BETWEEN ? AND ? AND estado = ? AND es.idexamen = ?;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, paciente.getIdPaciente());
            ps.setString(2, paciente.getFechaInicial());
            ps.setString(3, paciente.getFechaFinal());
            ps.setString(4, "FINALIZADA");
            ps.setInt(5, paciente.getIdExamen());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idSolicitud = rs.getInt("idsolicitud");
                int idLabotorio = rs.getInt("idlaboratorio");
                int idexamenes = rs.getInt("idexamenes_solicitados");    
                BigDecimal monto = rs.getBigDecimal("precio");

                ExamenesReportePaciente examen = new ExamenesReportePaciente(idSolicitud, idLabotorio, idexamenes, monto);
                
                
                examenes.add(examen);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return examenes;
        
    }
    
    public static List<HistorialMedicoReportePaciente> historialMedico(HistorialMedicoReportePaciente paciente){
        Connection conexion = Conexion.getConnection();
        List<HistorialMedicoReportePaciente> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta, idmedico, idespecialidad, precio, informe  FROM consulta WHERE "
                + "idpaciente = ? AND fecha_creacion BETWEEN ? AND ? AND estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, paciente.getIdPaciente());
            ps.setString(2, paciente.getFechaInicial());
            ps.setString(3, paciente.getFechaFinal());
            ps.setString(4, "FINALIZADA");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idConsulta = rs.getInt("idconsulta");
                int idMedico = rs.getInt("idmedico");
                int idEspecialidad = rs.getInt("idespecialidad");
                BigDecimal precio = rs.getBigDecimal("precio");
                String informe = rs.getString("informe");
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                List<ExamenSolicitado> examenes = RevisarExamenConsulta.examenesConsulta(idConsulta);
                
                HistorialMedicoReportePaciente solicitud = new HistorialMedicoReportePaciente(idConsulta, idMedico, idEspecialidad, precio, informe, examenes);
                
                solicitudes.add(solicitud);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return solicitudes;
    }
}
