
package com.rudyreyes.proyecto2.ipc2.backend.data.datalab;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RegistrarEspecialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamen;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrarNuevoExamen {
    
    public static void nuevoTipoExamen(TipoExamen examen){
        
        int admin = RegistrarEspecialidad.obtenerAdmin();
        
        PreparedStatement ps = null;
        String query = "INSERT INTO tipo_examen(nombre, descripcion, idadmin, idlaboratorio, estado) VALUES(?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setString(1, examen.getNombre());
            ps.setString(2, examen.getDescripcion());
            ps.setInt(3, admin);
            ps.setInt(4, examen.getIdLaboratorio());
            ps.setString(5, "PENDIENTE");
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void aceptarExamen(TipoExamen examen){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE tipo_examen set estado=? where idtipo_examen =?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, examen.getEstado());
            ps.setInt(2, examen.getIdTipo());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
