/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObtenerTipoExamen {
    public static List<TipoExamen> getTipos(String estado){
        
        List<TipoExamen> tipos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT idtipo_examen, nombre, descripcion FROM tipo_examen"
                    + " WHERE estado = ?";
        
        try {
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setString(1,estado);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idtipo_examen");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                 
                tipos.add(new TipoExamen(id, nombre, descripcion));
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        Conexion.cerrarConexion();
        return tipos;
        
    }
}
