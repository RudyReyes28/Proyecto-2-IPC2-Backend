/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AgregarSaldoMedico {
    
    public static void sumarSaldo(BigDecimal saldo, int idMedico){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE medico SET saldo = saldo + ? WHERE idmedico=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1,saldo);
            ps.setInt(2, idMedico);
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
