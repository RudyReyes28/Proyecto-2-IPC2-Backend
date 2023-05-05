
package com.rudyreyes.proyecto2.ipc2.backend.data.datalab;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamenLab;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgregarExamen {
    
    public static void registrarExamen(TipoExamenLab examen){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO examen_laboratorio(idlaboratorio, idtipo, precio) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, examen.getIdLaboratorio());
            ps.setInt(2, examen.getIdTipo());
            ps.setBigDecimal(3, examen.getPrecio());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static List<TipoExamenLab> getExamenesLab(int idLab){
        
        List<TipoExamenLab> tipos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT idtipo, precio FROM examen_laboratorio"
                    + " WHERE idlaboratorio = ?";
        
        try {
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idLab);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idtipo");
                BigDecimal precio = rs.getBigDecimal("precio");
                
                 
                tipos.add(new TipoExamenLab(id, precio));
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return tipos;
        
    }
    
    public static void modificarExamen(TipoExamenLab examen){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE examen_laboratorio SET precio=? WHERE idTipo =? AND idlaboratorio = ?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1, examen.getPrecio());
            ps.setInt(2, examen.getIdTipo());
            ps.setInt(3, examen.getIdLaboratorio());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
