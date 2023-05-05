
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.Especialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObtenerEspecialidades {
    
    public static List<Especialidad> getEspecialidades(String estado){
        Connection conexion = Conexion.getConnection();
        List<Especialidad> especialidad = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idespecialidad, nombre, descripcion FROM especialidad"
                    + " WHERE estado = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            ps.setString(1,estado);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idespecialidad");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                Especialidad es = new Especialidad(id, nombre, descripcion);
                especialidad.add(es);
                
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return especialidad;
        
    }
}
