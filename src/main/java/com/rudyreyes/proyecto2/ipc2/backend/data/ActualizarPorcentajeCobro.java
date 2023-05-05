
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActualizarPorcentajeCobro {
    
    public static void agregarPorcentaje(PorcentajeCobro porcentaje){
        
        String fechaHoraActual = Utilidades.horaActual();
        PreparedStatement ps = null;
        String query = "INSERT INTO porcentaje_cobro(porcentaje, fecha_activacion, idadmin, estado) VALUES(?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setDouble(1, porcentaje.getPorcentaje());
            ps.setString(2, fechaHoraActual);
            ps.setInt(3, porcentaje.getIdAdmin());
            ps.setString(4, "ACTIVADO");
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
    public static void desactivarPorcentaje(){
        String hora = Utilidades.horaActual();
        PorcentajeCobro porcentaje = obtenerPorcentaje();
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE porcentaje_cobro set fecha_desativacion=?, estado=? WHERE idporcentaje=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, hora);
            ps.setString(2,"DESACTIVADO");
            ps.setInt(3, porcentaje.getIdPorcentaje());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static PorcentajeCobro obtenerPorcentaje(){
        
        PorcentajeCobro porcentajeC = null;
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idporcentaje, porcentaje FROM porcentaje_cobro";
        
        try {
            
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idporcentaje");
                double porcentaje = rs.getDouble("porcentaje");
                
                porcentajeC = new PorcentajeCobro(id, porcentaje);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return porcentajeC;
        
    }
    
    
    
}
