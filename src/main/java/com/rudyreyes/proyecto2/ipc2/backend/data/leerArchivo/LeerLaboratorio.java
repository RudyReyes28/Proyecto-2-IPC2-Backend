/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.data.leerArchivo;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RegistrarEspecialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class LeerLaboratorio {
    
    public static void registrarLaboratorio(Usuario usuario){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO laboratorio(idlaboratorio, tipo_usuario, nombre, usuario, contraseña, direccion, cui, "
                + "telefono, correo, fecha_fundacion, saldo) values (?,?,?,?,?,?,?,?,?,?,?)";
        
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
    
    public static void nuevoTipoExamen(TipoExamen examen){
        
        int admin = RegistrarEspecialidad.obtenerAdmin();
        
        PreparedStatement ps = null;
        String query = "INSERT INTO tipo_examen(idtipo_examen,nombre, descripcion, idadmin, estado) VALUES(?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, examen.getIdTipo());
            ps.setString(2, examen.getNombre());
            ps.setString(3, examen.getDescripcion());
            ps.setInt(4, admin);
            ps.setString(5, "ACEPTADA");
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void registrarTipoExamen(int idLab, int idTipo, BigDecimal precio){
        PreparedStatement ps = null;
        String query = "INSERT INTO examen_laboratorio(idlaboratorio, idtipo, precio) VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idLab);
            ps.setInt(2, idTipo);
            ps.setBigDecimal(3, precio);
            ps.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static void solicitarExamenes(SolicitudExamen solicitud){
        
        PreparedStatement ps = null;
        String query = "INSERT INTO solicitud_examen(idsolicitud, idpaciente, idlaboratorio, porcentaje, "
                + "fecha_solicitado, estado) VALUES(?,?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, solicitud.getIdSolicitud());
            ps.setInt(2, solicitud.getIdPaciente());
            ps.setInt(3, solicitud.getIdLaboratorio());
            ps.setDouble(4, solicitud.getPorcentaje());
            ps.setString(5, solicitud.getFecha_solicitado());
            ps.setString(6, solicitud.getEstado());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void agregarExamenes(int idSolicitud, int idExamen, BigDecimal precio){
        PreparedStatement ps = null;
        String query = "INSERT INTO examenes_solicitados(idsolicitud, idexamen, precio) "
                + " VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idSolicitud);
            ps.setInt(2, idExamen);
            ps.setBigDecimal(3,precio);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
