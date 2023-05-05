/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datalab;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AgregarSaldoLab {
    
    public static void sumarSaldo(BigDecimal saldo, int idLab){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE laboratorio SET saldo = saldo + ? WHERE idlaboratorio=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setBigDecimal(1,saldo);
            ps.setInt(2, idLab);
            
            int resultado = ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
