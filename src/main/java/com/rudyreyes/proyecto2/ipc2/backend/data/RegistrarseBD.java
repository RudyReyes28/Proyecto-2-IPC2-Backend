/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrarseBD {
    
    
    public static void registrarUsuario(Usuario usuario){
        
        String nombreTabla = nombreTabla(usuario);
        String fechaTipo = tipoFecha(usuario);
        PreparedStatement ps = null;
        String query = "INSERT INTO "+nombreTabla+"(tipo_usuario, nombre, usuario, contraseña, direccion, cui, "
                + "telefono, correo,"+fechaTipo+", saldo) values (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setString(1, usuario.getTipoUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getDireccion());
            ps.setInt(6, usuario.getCUI());
            ps.setInt(7, usuario.getTelefono());
            ps.setString(8, usuario.getCorreo());
            ps.setString(9, usuario.getFechaNacimiento());
            ps.setDouble(10, 0);
            ps.executeUpdate();
            Conexion.cerrarConexion();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private static String tipoFecha(Usuario usuario){
        if(usuario.getTipoUsuario().equalsIgnoreCase("LABORATORIO")){
            return "fecha_fundacion";
        }else{
            return "fecha_nacimiento";
        }
    }
    
    private static String nombreTabla(Usuario usuario){
        if(usuario.getTipoUsuario().equalsIgnoreCase("LABORATORIO")){
            return "laboratorio";
        
        }else if(usuario.getTipoUsuario().equalsIgnoreCase("PACIENTE")){
            return "paciente";
        
        }else{
            return "medico";
        }
    }
}
