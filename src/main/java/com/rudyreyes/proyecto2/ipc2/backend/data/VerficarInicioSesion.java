
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamenLab;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VerficarInicioSesion {
    
    public static boolean verificarMedico(Usuario usuario){
        Connection conexion = Conexion.getConnection();
        EspecialidadDelMedico especialidad = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idespecialidad, precio FROM especialidad_medico"
                    + " WHERE idmedico = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            ps.setInt(1,usuario.getIdUsuario());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idespecialidad");
                BigDecimal precio = rs.getBigDecimal("precio");
                
                especialidad = new EspecialidadDelMedico(id, precio);
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        Conexion.cerrarConexion();
        
        if(especialidad != null){
            return false;
        }else{
            return true;
        }
        
    }
    
    public static boolean verificarLab(Usuario usuario){
        Connection conexion = Conexion.getConnection();
        TipoExamenLab examen = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idtipo, precio FROM examen_laboratorio"
                    + " WHERE idlaboratorio = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            ps.setInt(1,usuario.getIdUsuario());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("idtipo");
                BigDecimal precio = rs.getBigDecimal("precio");
                
                examen = new TipoExamenLab(id, precio);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        Conexion.cerrarConexion();
        
        
        if(examen != null){
            return false;
        }else{
            return true;
        }
        
        
    }
}
