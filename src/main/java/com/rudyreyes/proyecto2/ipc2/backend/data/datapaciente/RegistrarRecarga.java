
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.RecargaPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrarRecarga {
    
    public static void realizarRecarga(RecargaPaciente recarga){
        actualizarSaldo(recarga);
        
        String fechaHoraActual = Utilidades.horaActual();
        PreparedStatement ps = null;
        String query = "INSERT INTO recarga(idpaciente, fecha, monto) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, recarga.getIdPaciente());
            ps.setString(2, fechaHoraActual);
            ps.setBigDecimal(3, recarga.getMonto());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    private static void actualizarSaldo(RecargaPaciente recarga){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE paciente SET saldo = saldo + ? WHERE idpaciente=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1, recarga.getMonto());
            ps.setInt(2, recarga.getIdPaciente());
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
