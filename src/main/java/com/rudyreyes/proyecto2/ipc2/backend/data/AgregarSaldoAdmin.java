/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class AgregarSaldoAdmin {
    
    public static void sumarSaldo(BigDecimal saldo){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE administrador SET saldo = saldo + ?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1,saldo);
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
