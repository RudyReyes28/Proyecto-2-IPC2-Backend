/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rudy-reyes
 */
public class ObtenerUsuarios {
    
    public static Usuario obtenerUsuario(String nombreTabla, String nombreID,int idUsuario){
        Connection conexion = Conexion.getConnection();
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  nombre, usuario, correo, fecha_nacimiento, saldo FROM "+ nombreTabla +" WHERE "+ nombreID+" = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            ps.setInt(1, idUsuario);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("usuario");
                String correo = rs.getString("correo");
                String fechaNacimiento = rs.getString("fecha_nacimiento");
                BigDecimal saldo = new BigDecimal(rs.getDouble("saldo"));
                
                usuario = new Usuario(nombre, nombreUsuario, correo, fechaNacimiento, saldo);
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
    public static Usuario obtenerLaboratorio(int idLab){
        Connection conexion = Conexion.getConnection();
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  nombre, usuario, correo, fecha_fundacion, saldo FROM laboratorio WHERE idlaboratorio = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            ps.setInt(1, idLab);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("usuario");
                String correo = rs.getString("correo");
                String fechaNacimiento = rs.getString("fecha_fundacion");
                BigDecimal saldo = new BigDecimal(rs.getDouble("saldo"));
                
                usuario = new Usuario(nombre, nombreUsuario, correo, fechaNacimiento, saldo);
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
}
