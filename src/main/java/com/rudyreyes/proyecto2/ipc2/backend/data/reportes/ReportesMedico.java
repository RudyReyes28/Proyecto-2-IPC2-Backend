/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.reportes;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.PacienteEspReporteMedico;
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
public class ReportesMedico {
    
    /*
    
    SELECT p.nombre AS nombre_paciente, COUNT(c.idconsulta) AS cantidad_consultas, SUM(c.precio-c.precio*c.porcentaje_app) AS total_ingreso
FROM paciente p
INNER JOIN consulta c ON p.idpaciente = c.idpaciente
WHERE idmedico= ? AND c.fecha_creacion BETWEEN ? AND ?
GROUP BY p.idpaciente
ORDER BY total_ingreso DESC
LIMIT 5;

SELECT e.nombre AS especialidad, COUNT(*) AS cantidad_consultas, SUM(c.precio-c.precio*c.porcentaje_app) AS total_ingresos
FROM consulta c
INNER JOIN especialidad e ON c.idespecialidad = e.idespecialidad
WHERE c.idmedico = 3 AND c.fecha_creacion BETWEEN '2023-03-03' AND '2023-05-10'
GROUP BY c.idespecialidad
ORDER BY total_ingresos DESC
LIMIT 5;
    
    
    
    */
    
    public static List<PacienteEspReporteMedico> reportesPaciente(PacienteEspReporteMedico fechas){
        
        List<PacienteEspReporteMedico> pacientes = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT p.nombre AS nombre_paciente, COUNT(c.idconsulta) AS cantidad_consultas, "
                + "SUM(c.precio-c.precio*c.porcentaje_app) AS total_ingreso "
                + "FROM paciente p INNER JOIN consulta c ON p.idpaciente = c.idpaciente "
                + "WHERE idmedico= ? AND c.fecha_creacion BETWEEN ? AND ? "
                + "GROUP BY p.idpaciente ORDER BY total_ingreso DESC LIMIT 5;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, fechas.getIdMedico());
            ps.setString(2, fechas.getFechaInicial());
            ps.setString(3, fechas.getFechaFinal());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombrePaciente = rs.getString("nombre_paciente");
                int cantConsultas = rs.getInt("cantidad_consultas");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingreso");
                
                PacienteEspReporteMedico paciente = new PacienteEspReporteMedico(nombrePaciente, cantConsultas, totalIngresos);
                
                pacientes.add(paciente);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return pacientes;
    }
    
    public static List<PacienteEspReporteMedico> reportesEspecialidad(PacienteEspReporteMedico fechas){
        
        List<PacienteEspReporteMedico> especialidades = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT e.nombre AS especialidad, COUNT(*) AS cantidad_consultas, "
                + "SUM(c.precio-c.precio*c.porcentaje_app) AS total_ingresos "
                + "FROM consulta c INNER JOIN especialidad e ON c.idespecialidad = e.idespecialidad "
                + "WHERE c.idmedico = ? AND c.fecha_creacion BETWEEN ? AND ? "
                + "GROUP BY c.idespecialidad ORDER BY total_ingresos DESC LIMIT 5;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, fechas.getIdMedico());
            ps.setString(2, fechas.getFechaInicial());
            ps.setString(3, fechas.getFechaFinal());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombreEspecialidad = rs.getString("especialidad");
                int cantConsultas = rs.getInt("cantidad_consultas");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                
                PacienteEspReporteMedico consulta = new PacienteEspReporteMedico(nombreEspecialidad, cantConsultas, totalIngresos);
                
                especialidades.add(consulta);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return especialidades;
    }
}
