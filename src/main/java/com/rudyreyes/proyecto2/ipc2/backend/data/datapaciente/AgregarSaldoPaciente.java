/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.RecargaPaciente;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AgregarSaldoPaciente {
    
    public static void restarSaldo(BigDecimal saldo, int idPaciente){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE paciente SET saldo = saldo - ? WHERE idpaciente=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1,saldo);
            ps.setInt(2, idPaciente);
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
