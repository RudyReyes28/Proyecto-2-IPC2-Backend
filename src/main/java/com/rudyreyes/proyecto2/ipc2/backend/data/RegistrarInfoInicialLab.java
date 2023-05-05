
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.InfoInicialLab;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamenLab;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrarInfoInicialLab {
    
    public static void registrarInformacion(InfoInicialLab informacion){
        for(TipoExamenLab tp: informacion.getExamenes()){
            registrarTipoExamen(informacion.getIdLaboratorio(), tp);
        }
        
    }
    
    private static void registrarTipoExamen(int idLab, TipoExamenLab examen){
        PreparedStatement ps = null;
        String query = "INSERT INTO examen_laboratorio(idlaboratorio, idtipo, precio) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idLab);
            ps.setInt(2, examen.getIdTipo());
            ps.setBigDecimal(3, examen.getPrecio());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
