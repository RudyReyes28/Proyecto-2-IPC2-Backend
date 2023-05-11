/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.reportes;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.ConsultaExamenReporteAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.LabReporteAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.MedicoReporteAdmin;
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
public class ReportesAdmin {
    
    /*
    
    SELECT porcentaje, fecha_activacion, fecha_desativacion FROM porcentaje_cobro WHERE estado = 'DESACTIVADO';

    SELECT m.nombre AS nombre_medico, COUNT(c.idconsulta) AS cantidad_consultas, SUM(c.precio-c.precio*c.porcentaje_app) AS total_ingresos,
    SUM(c.precio*c.porcentaje_app) AS total_app
    FROM medico m
JOIN examen c ON m.idmedico = c.idmedico
GROUP BY m.idmedico
ORDER BY total_ingresos DESC
LIMIT 5;

SELECT l.nombre AS nombre_laboratorio, COUNT(e.idsolicitud) AS cantidad_examenes, SUM(e.precio-e.precio*s.porcentaje) AS total_ingresos,
SUM(e.precio*s.porcentaje) AS total_app
FROM laboratorio l
JOIN solicitud_examen s ON l.idlaboratorio = s.idlaboratorio
JOIN examenes_solicitados e ON s.idsolicitud = e.idsolicitud
GROUP BY l.idlaboratorio
ORDER BY total_ingresos DESC
LIMIT 5;

SELECT COUNT(*) AS cantidad_consultas, SUM(precio-precio*porcentaje_app) AS total_ingresos,
SUM(precio*porcentaje_app) AS total_app
FROM examen
WHERE fecha_creacion BETWEEN '2023-03-03' AND '2023-05-10';

SELECT COUNT(*) AS cantidad_examenes, SUM(e.precio-e.precio*s.porcentaje) AS total_ingresos,
SUM(e.precio*s.porcentaje) AS total_app
FROM  solicitud_examen s JOIN examenes_solicitados e ON s.idsolicitud = e.idsolicitud
WHERE s.fecha_solicitado BETWEEN '2023-03-03' AND '2023-05-10';

    
    */

    public static List <PorcentajeCobro> reportePorcentaje(){
        List <PorcentajeCobro> porcentajes = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT porcentaje, fecha_activacion, fecha_desativacion FROM porcentaje_cobro WHERE estado = 'DESACTIVADO';";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                double porcentaje = rs.getDouble("porcentaje");
                String fechaActivacion = rs.getString("fecha_activacion");
                String fechaDesactivacion = rs.getString("fecha_desativacion");
                
                PorcentajeCobro pc= new PorcentajeCobro(porcentaje, fechaActivacion, fechaDesactivacion);
                
                porcentajes.add(pc);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return porcentajes;
    }
    
    
    public static List <MedicoReporteAdmin> reporteMedicos(){
        List <MedicoReporteAdmin> medicos = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT m.nombre AS nombre_medico, COUNT(c.idconsulta) AS cantidad_consultas, SUM(c.precio-c.precio*c.porcentaje_app) AS total_ingresos, "
                + "SUM(c.precio*c.porcentaje_app) AS total_app "
                + "FROM medico m JOIN consulta c ON m.idmedico = c.idmedico "
                + "GROUP BY m.idmedico ORDER BY total_ingresos DESC LIMIT 5;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombreMedico = rs.getString("nombre_medico");
                int cantConsultas = rs.getInt("cantidad_consultas");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                BigDecimal totalApp = rs.getBigDecimal("total_app");
                
                MedicoReporteAdmin reporte = new MedicoReporteAdmin(nombreMedico, cantConsultas, totalIngresos, totalApp);
                
                medicos.add(reporte);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return medicos;
    }
    
    public static List <LabReporteAdmin> reporteLaboratorios(){
        List <LabReporteAdmin> laboratorios = new ArrayList<>();
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT l.nombre AS nombre_laboratorio, COUNT(e.idsolicitud) AS cantidad_examenes, "
                + "SUM(e.precio-e.precio*s.porcentaje) AS total_ingresos, "
                + "SUM(e.precio*s.porcentaje) AS total_app FROM laboratorio l "
                + "JOIN solicitud_examen s ON l.idlaboratorio = s.idlaboratorio "
                + "JOIN examenes_solicitados e ON s.idsolicitud = e.idsolicitud "
                + "GROUP BY l.idlaboratorio ORDER BY total_ingresos DESC LIMIT 5;";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombreLab = rs.getString("nombre_laboratorio");
                int cantExamenes = rs.getInt("cantidad_examenes");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                BigDecimal totalApp = rs.getBigDecimal("total_app");
                
                LabReporteAdmin reporte = new LabReporteAdmin(nombreLab, cantExamenes, totalIngresos, totalApp);
                
                laboratorios.add(reporte);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return laboratorios;
    }
    
    public static ConsultaExamenReporteAdmin reporteConsultas(ConsultaExamenReporteAdmin fechas){
        ConsultaExamenReporteAdmin consulta = null;
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) AS cantidad_consultas, "
                + "SUM(precio-precio*porcentaje_app) AS total_ingresos, "
                + "SUM(precio*porcentaje_app) AS total_app "
                + "FROM consulta WHERE fecha_creacion BETWEEN ? AND ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setString(1, fechas.getFechaInicial());
            ps.setString(2, fechas.getFechaFinal());
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                int cantidad = rs.getInt("cantidad_consultas");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                BigDecimal totalApp = rs.getBigDecimal("total_app");
                
                consulta = new ConsultaExamenReporteAdmin(cantidad, totalIngresos, totalApp);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return consulta;
    }
    
    public static ConsultaExamenReporteAdmin reporteExamenes(ConsultaExamenReporteAdmin fechas){
        ConsultaExamenReporteAdmin examen = null;
        
        Connection conexion = Conexion.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) AS cantidad_examenes, SUM(e.precio-e.precio*s.porcentaje) AS total_ingresos, "
                + "SUM(e.precio*s.porcentaje) AS total_app "
                + "FROM  solicitud_examen s JOIN examenes_solicitados e ON s.idsolicitud = e.idsolicitud "
                + "WHERE s.fecha_solicitado BETWEEN ? AND ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setString(1, fechas.getFechaInicial());
            ps.setString(2, fechas.getFechaFinal());
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                int cantidad = rs.getInt("cantidad_examenes");
                BigDecimal totalIngresos = rs.getBigDecimal("total_ingresos");
                BigDecimal totalApp = rs.getBigDecimal("total_app");
                
                examen = new ConsultaExamenReporteAdmin(cantidad, totalIngresos, totalApp);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return examen;
    }
    
}
