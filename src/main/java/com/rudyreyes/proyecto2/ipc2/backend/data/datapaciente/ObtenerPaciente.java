/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ObtenerPaciente {
    
    public static Usuario obtenerPaciente(int idPaciente){
        Connection conexion = Conexion.getConnection();
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT usuario, saldo FROM paciente WHERE idpaciente = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idPaciente);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombreUsuario = rs.getString("usuario");
                BigDecimal saldo = new BigDecimal(rs.getDouble("saldo"));
                
                usuario = new Usuario(nombreUsuario, saldo);
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
    public static String obtenerNombre(int idPaciente){
        Connection conexion = Conexion.getConnection();
        String nombrePaciente = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT nombre FROM paciente WHERE idpaciente = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idPaciente);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                nombrePaciente = rs.getString("nombre");
                
                
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return nombrePaciente;
    }
}
