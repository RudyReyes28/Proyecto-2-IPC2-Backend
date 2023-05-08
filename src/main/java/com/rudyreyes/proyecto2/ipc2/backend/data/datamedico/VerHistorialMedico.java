/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.FechaConsulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudy-reyes
 */
public class VerHistorialMedico {
    
    public static List<Consulta> historialMedico(){
        Connection conexion = Conexion.getConnection();
        List<Consulta> solicitudes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idconsulta, idpaciente, idmedico,informe  FROM consulta WHERE estado=?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setString(1, "FINALIZADA");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idConsulta = rs.getInt("idconsulta");
                int idPaciente = rs.getInt("idpaciente");
                int idMedico = rs.getInt("idmedico");
                String informe = rs.getString("informe");
                
                //OBTENER LA FECHA AGENDADA
                FechaConsulta fecha = obtenerFecha(idConsulta);
                
                //NECESITAMOS OBTENER LOS EXAMENES AGENDADOS
                List<ExamenSolicitado> examenes = RevisarExamenConsulta.examenesConsulta(idConsulta);
                
                Consulta solicitud = new Consulta(idConsulta, idPaciente, idMedico, fecha, informe, examenes);
                
                solicitudes.add(solicitud);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return solicitudes;
    }
    
    private static FechaConsulta obtenerFecha(int idConsulta){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        FechaConsulta fecha = null;
        
        String query = "SELECT  fecha FROM fecha_agendada "
                + " WHERE idconsulta = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idConsulta);

            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String fechaAgendada = rs.getString("fecha");
                fecha = new FechaConsulta(fechaAgendada);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return fecha;
        
        
    }
}
