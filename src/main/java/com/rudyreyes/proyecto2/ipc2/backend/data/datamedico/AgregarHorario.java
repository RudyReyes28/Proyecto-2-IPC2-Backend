
package com.rudyreyes.proyecto2.ipc2.backend.data.datamedico;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.HorarioMedico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgregarHorario {
    
    public static void registrarHorario(HorarioMedico horario){
        PreparedStatement ps = null;
        String query = "INSERT INTO horario_atencion(idmedico, hora_inicial, hora_final) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, horario.getIdMedico());
            ps.setString(2, horario.getHoraInicial());
            ps.setString(3, horario.getHoraFinal());
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static List<HorarioMedico> getHorarios(int idMedico){
        Connection conexion = Conexion.getConnection();
        List<HorarioMedico> horarios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT  idhorario, hora_inicial, hora_final FROM horario_atencion"
                + " WHERE idmedico = ?";
        
        try {
            
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idHorario = rs.getInt("idhorario");
                String horaInicial = rs.getString("hora_inicial");
                String horaFinal = rs.getString("hora_final");
                horarios.add(new HorarioMedico(horaInicial, horaFinal,idMedico, idHorario));
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return horarios;
    }
    
    public static void modificarHorario(HorarioMedico horario){
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE horario_atencion SET hora_inicial = ?, hora_final = ? WHERE idhorario = ?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, horario.getHoraInicial());
            ps.setString(2, horario.getHoraFinal());
            ps.setInt(3, horario.getIdHorario());
            
            int resultado = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
