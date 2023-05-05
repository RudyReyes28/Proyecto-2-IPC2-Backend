/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.ActualizarPorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.FechaConsulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class AgendarFechaConsulta {
    
    public static void agendarFecha(FechaConsulta fecha){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO fecha_agendada(idconsulta, fecha, idhorario, hora_inicial, "
                + "hora_final, estado) VALUES(?,?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, fecha.getIdConsulta());
            ps.setString(2, fecha.getFecha());
            ps.setInt(3, fecha.getIdHorario());
            ps.setString(4, fecha.getHoraInicial());
            ps.setString(5, fecha.getHoraFinal());
            ps.setString(6, "AGENDADA");
            ps.executeUpdate();
            
            //AHORA NECESITO MODIFICAR LA FECHA DE LA CONSULTA Y SUBIR LA GANANCIA AL ADMIN Y AL MEDICO
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
}
