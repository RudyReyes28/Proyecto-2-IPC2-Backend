/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.leerArchivo;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class LeerAdministrador {
    
    public static void registrarAdmin(Usuario usuario){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO administrador (idadmin,tipo_usuario, nombre, usuario, contraseña, "
                + " correo,fecha_nacimiento, saldo) values (?,?,?,?,?,?,?,?)";
        
        try {
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getTipoUsuario());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContraseña());
            ps.setString(6, usuario.getCorreo());
            ps.setString(7, usuario.getFechaNacimiento());
            ps.setBigDecimal(8, usuario.getSaldo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void agregarPorcentaje(int idadmin){
        
        String fechaHoraActual = Utilidades.horaActual();
        PreparedStatement ps = null;
        String query = "INSERT INTO porcentaje_cobro(porcentaje, fecha_activacion, idadmin, estado) VALUES(?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setDouble(1, 0.04);
            ps.setString(2, fechaHoraActual);
            ps.setInt(3, idadmin);
            ps.setString(4, "ACTIVADO");
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
}
