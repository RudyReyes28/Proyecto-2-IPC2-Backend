/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.reportes;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.PacienteEspReporteMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.PacienteExReporteLab;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudy-reyes
 */
public class ReportesLaboratorio {
    
    /*
    SELECT p.nombre AS nombre_paciente, COUNT(es.idexamenes_solicitados) AS cantidad_examenes, SUM(es.precio-es.precio*se.porcentaje) AS total_ingresos
FROM paciente p
INNER JOIN solicitud_examen se ON se.idpaciente = p.idpaciente
INNER JOIN examenes_solicitados es ON es.idsolicitud = se.idsolicitud
WHERE se.idlaboratorio = 3 AND se.fecha_solicitado BETWEEN '2023-01-01' AND '2023-05-11'
GROUP BY p.idpaciente
ORDER BY total_ingresos DESC
LIMIT 5;

SELECT te.nombre AS tipo_examen, COUNT(es.idexamenes_solicitados) AS cantidad_examenes, SUM(es.precio-es.precio*se.porcentaje) AS total_ingresos 
FROM examenes_solicitados es 
JOIN tipo_examen te ON es.idexamen = te.idtipo_examen 
JOIN solicitud_examen se ON es.idsolicitud = se.idsolicitud 
WHERE se.idlaboratorio = 3 AND se.fecha_solicitado  BETWEEN '2023-03-03' AND '2023-05-10'
GROUP BY te.nombre 
ORDER BY total_ingresos DESC 
LIMIT 5;
    */
    
    
    public static List<PacienteExReporteLab> reportesExamenes(PacienteExReporteLab fechas){
        
        List<PacienteExReporteLab> pacientes = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT te.nombre AS tipo_examen, COUNT(es.idexamenes_solicitados) AS cantidad_examenes, "
                + "SUM(es.precio-es.precio*se.porcentaje) AS total_ingresos " +
"FROM examenes_solicitados es JOIN tipo_examen te ON es.idexamen = te.idtipo_examen " +
"JOIN solicitud_examen se ON es.idsolicitud = se.idsolicitud " +
"WHERE se.idlaboratorio = ? AND se.fecha_solicitado  BETWEEN ? AND ? " +
"GROUP BY te.nombre ORDER BY total_ingresos DESC LIMIT 5;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, fechas.getIdLaboratorio());
            ps.setString(2, fechas.getFechaInicial());
            ps.setString(3, fechas.getFechaFinal());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombrePaciente = rs.getString("tipo_examen");
                int cantExamenes = rs.getInt("cantidad_examenes");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                
                PacienteExReporteLab paciente = new PacienteExReporteLab(nombrePaciente, cantExamenes, totalIngresos);
                
                pacientes.add(paciente);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return pacientes;
    }
    
    public static List<PacienteExReporteLab> reportesPaciente(PacienteExReporteLab fechas){
        
        List<PacienteExReporteLab> examenes = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT p.nombre AS nombre_paciente, COUNT(es.idexamenes_solicitados) AS cantidad_examenes,"
                + " SUM(es.precio-es.precio*se.porcentaje) AS total_ingresos " +
"FROM paciente p INNER JOIN solicitud_examen se ON se.idpaciente = p.idpaciente " +
"INNER JOIN examenes_solicitados es ON es.idsolicitud = se.idsolicitud " +
"WHERE se.idlaboratorio = ? AND se.fecha_solicitado BETWEEN ? AND ? " +
"GROUP BY p.idpaciente ORDER BY total_ingresos DESC LIMIT 5;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, fechas.getIdLaboratorio());
            ps.setString(2, fechas.getFechaInicial());
            ps.setString(3, fechas.getFechaFinal());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombreExamen = rs.getString("nombre_paciente");
                int cantExamenes = rs.getInt("cantidad_examenes");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                
                PacienteExReporteLab examen = new PacienteExReporteLab(nombreExamen, cantExamenes, totalIngresos);
                
                examenes.add(examen);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return examenes;
    }
    
    
    
}
