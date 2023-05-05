
package com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.ActualizarPorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.data.AgregarSaldoAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.datalab.AgregarExamen;
import com.rudyreyes.proyecto2.ipc2.backend.data.datalab.AgregarSaldoLab;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Laboratorio;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamenLab;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SolicitarExamenes {
    
    public static List<Laboratorio> obtenerLaboratorios(){
        Connection conexion = Conexion.getConnection();
        List<Laboratorio> laboratorios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT idlaboratorio, nombre,usuario, saldo FROM laboratorio";
        
        try {
            
            ps = conexion.prepareStatement(query);
            
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int idLab = rs.getInt("idlaboratorio");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("usuario");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                //NECESITAMOS OBTENER LOS EXAMENES
                List<TipoExamenLab> examenes = AgregarExamen.getExamenesLab(idLab);
                
                Laboratorio lab = new Laboratorio(idLab, nombre, nombreUsuario, saldo, examenes);
                
                laboratorios.add(lab);
                
            }
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return laboratorios;
    }
    
    public static void solicitarExamenes(SolicitudExamen solicitud){
        PorcentajeCobro porcentaje = ActualizarPorcentajeCobro.obtenerPorcentaje();
        String fechaActual = Utilidades.obtenerFechaActual();
        PreparedStatement ps = null;
        String query = "INSERT INTO solicitud_examen(idpaciente, idlaboratorio, porcentaje, "
                + "fecha_solicitado, estado) VALUES(?,?,?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, solicitud.getIdPaciente());
            ps.setInt(2, solicitud.getIdLaboratorio());
            ps.setDouble(3, porcentaje.getPorcentaje());
            ps.setString(4, fechaActual);
            ps.setString(5, "PENDIENTE");
            ps.executeUpdate();
            
            //AHORA TENEMOS QUE AGREGAR LOS EXAMENES Y SUBIR LA GANANCIA AL ADMIN Y AL MEDICO
            int idSolicitud = obtenerIDSolicitud();
            BigDecimal total = BigDecimal.ZERO;
            for(ExamenSolicitado ex: solicitud.getExamenes()){
                ex.setIdSolicitud(idSolicitud);
                agregarExamenes(ex);
                total = total.add(ex.getPrecio());
                
            }
            
            
            //AHORA TOCA LAS GANANCIAS
            BigDecimal saldoAdmin = total.multiply(BigDecimal.valueOf(porcentaje.getPorcentaje()));
            AgregarSaldoAdmin.sumarSaldo(saldoAdmin);
            //AHORA TOCA LABORATORIO
            BigDecimal saldoMedico = total.subtract(saldoAdmin);
            AgregarSaldoLab.sumarSaldo(saldoMedico, solicitud.getIdLaboratorio());
            //AHORA TOCA EL PACIENTE
            AgregarSaldoPaciente.restarSaldo(total, solicitud.getIdPaciente());
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public static int obtenerIDSolicitud(){
        
        Connection conexion = Conexion.getConnection();
        PreparedStatement ps = null;
        int idConsulta = 0;
        ResultSet rs = null;
        String query = "SELECT  idsolicitud FROM solicitud_examen";
        
        try {
            
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                idConsulta = rs.getInt("idsolicitud");
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return idConsulta;
    }
    
    public static void agregarExamenes(ExamenSolicitado examenes){
        PreparedStatement ps = null;
        String query = "INSERT INTO examenes_solicitados(idsolicitud, idexamen, precio) "
                + " VALUES(?,?,?)";
        
        try{
            Connection conexion = Conexion.getConnection();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, examenes.getIdSolicitud());
            ps.setInt(2, examenes.getIdExamen());
            ps.setBigDecimal(3, examenes.getPrecio());
            ps.executeUpdate();
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
}
