/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Especialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgregarEspecialidad {
    
    public static void registrarEspecialidad(EspecialidadDelMedico especialidad){
        PreparedStatement ps = null;
        String query = "INSERT INTO especialidad_medico(idmedico, idespecialidad, precio) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, especialidad.getIdMedico());
            ps.setInt(2, especialidad.getId());
            ps.setBigDecimal(3, especialidad.getPrecio());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static List<EspecialidadDelMedico> getEspecialidadesMedico(int idMedico){
        Connection conexion = Conexion.getConnection();
        List<EspecialidadDelMedico> especialidad = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idespecialidad, precio FROM especialidad_medico"
                + " WHERE idmedico = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idespecialidad");
                BigDecimal precio = rs.getBigDecimal("precio");
                
                EspecialidadDelMedico es = new EspecialidadDelMedico(id, precio);
                especialidad.add(es);
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return especialidad;
        
    }
    
    public static void modificarEspecialidad(EspecialidadDelMedico especialidad){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE especialidad_medico SET precio=? WHERE idespecialidad = ? AND idmedico = ?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1, especialidad.getPrecio());
            ps.setInt(2, especialidad.getId());
            ps.setInt(3, especialidad.getIdMedico());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
