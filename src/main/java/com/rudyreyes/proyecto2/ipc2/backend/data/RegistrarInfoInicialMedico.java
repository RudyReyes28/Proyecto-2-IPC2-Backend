/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.HorarioMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.InfoInicialMedico;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrarInfoInicialMedico {
    
    public static void registrarInformacionInicial(InfoInicialMedico informacion){
        
        for(EspecialidadDelMedico es: informacion.getEspecialidades()){
            registrarEspecialidad(informacion.getIdMedico(), es);
        }
        
        for(HorarioMedico hr: informacion.getHorarios()){
            registrarHorario(informacion.getIdMedico(), hr);
        }
        
        
    }
    
    private static void registrarEspecialidad(int idMedico, EspecialidadDelMedico especialidad){
        PreparedStatement ps = null;
        String query = "INSERT INTO especialidad_medico(idmedico, idespecialidad, precio) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setInt(2, especialidad.getId());
            ps.setBigDecimal(3, especialidad.getPrecio());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    private static void registrarHorario(int idMedico, HorarioMedico horario){
        PreparedStatement ps = null;
        String query = "INSERT INTO horario_atencion(idmedico, hora_inicial, hora_final) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setString(2, horario.getHoraInicial());
            ps.setString(3, horario.getHoraFinal());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
