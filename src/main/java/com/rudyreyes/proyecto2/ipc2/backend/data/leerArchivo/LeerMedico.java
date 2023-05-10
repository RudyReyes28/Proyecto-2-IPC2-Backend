/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.leerArchivo;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RegistrarEspecialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Especialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class LeerMedico {
    
    public static void registrarMedico(Usuario usuario){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO medico(idmedico, tipo_usuario, nombre, usuario, contraseña, direccion, cui, "
                + "telefono, correo, fecha_nacimiento, saldo) values (?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getTipoUsuario());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContraseña());
            ps.setString(6, usuario.getDireccion());
            ps.setInt(7, usuario.getCUI());
            ps.setInt(8, usuario.getTelefono());
            ps.setString(9, usuario.getCorreo());
            ps.setString(10, usuario.getFechaNacimiento());
            ps.setBigDecimal(11, usuario.getSaldo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void nuevaEspecialidad(Especialidad especialidad){
        
        int admin = RegistrarEspecialidad.obtenerAdmin();
        
        PreparedStatement ps = null;
        String query = "INSERT INTO especialidad(idespecialidad,nombre, descripcion, idadmin, estado) VALUES(?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, especialidad.getIdEspecialidad());
            ps.setString(2, especialidad.getNombre());
            ps.setString(3, especialidad.getDescripcion());
            ps.setInt(4, admin);
            ps.setString(5, "ACEPTADA");
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void registrarHorario(int idMedico, String horaInicial, String horaFinal){
        PreparedStatement ps = null;
        String query = "INSERT INTO horario_atencion(idmedico, hora_inicial, hora_final) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setString(2,horaInicial);
            ps.setString(3, horaFinal);
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void registrarEspecialidad(int idMedico, int idEsp, BigDecimal precio){
        PreparedStatement ps = null;
        String query = "INSERT INTO especialidad_medico(idmedico, idespecialidad, precio) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idMedico);
            ps.setInt(2, idEsp );
            ps.setBigDecimal(3, precio);
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
