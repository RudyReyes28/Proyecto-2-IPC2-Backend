/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.LoginBD;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Especialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rudy-reyes
 */
public class RegistrarEspecialidad {
    
    public static void nuevaEspecialidad(Especialidad especialidad){
        
        int admin = obtenerAdmin();
        
        PreparedStatement ps = null;
        String query = "INSERT INTO especialidad(nombre, descripcion, idadmin, idmedico, estado) VALUES(?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setString(1, especialidad.getNombre());
            ps.setString(2, especialidad.getDescripcion());
            ps.setInt(3, admin);
            ps.setInt(4, especialidad.getIdMedico());
            ps.setString(5, "PENDIENTE");
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void aceptarEspecialidad(Especialidad especialidad){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE especialidad set estado=? where idespecialidad =?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, especialidad.getEstado());
            ps.setInt(2, especialidad.getIdEspecialidad());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int obtenerAdmin(){
        Connection conexion = Conexion.getConnection();
        int id=0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idadmin FROM administrador";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("idadmin");
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return id;
    }

}
