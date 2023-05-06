
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginBD {
    
    public static Usuario obtenerUsuario(Usuario usser, String idTipoUsuario, String nombreTabla){
        Connection conexion = Conexion.getConnection();
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT "+idTipoUsuario + ", tipo_usuario, nombre, usuario, saldo FROM "+nombreTabla
                    + " WHERE usuario = ? AND contraseña = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            ps.setString(1, usser.getUsuario());
            ps.setString(2, usser.getContraseña());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(idTipoUsuario);
                String tipo = rs.getString("tipo_usuario");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("usuario");
                BigDecimal saldo = new BigDecimal(rs.getDouble("saldo"));
                
                usuario = new Usuario(id, tipo, nombre, nombreUsuario, saldo);
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
    public static Usuario validarUsuario(Usuario usser){
        Usuario usuario = null;
        
        if((usuario = obtenerUsuario(usser, "idadmin", "administrador")) != null ){
            
            return usuario;
        }else if((usuario = obtenerUsuario(usser, "idpaciente", "paciente")) != null ){
            
            return usuario;
        }else if((usuario = obtenerUsuario(usser, "idmedico", "medico")) != null ){
            
            return usuario;
        }else if((usuario = obtenerUsuario(usser, "idlaboratorio", "laboratorio")) != null ){
            
            return usuario;
        }else{
            
            return usuario;
        }
        
        
    }
}
