/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_admin;

import com.rudyreyes.proyecto2.ipc2.backend.data.reportes.ReportesAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.ConsultaExamenReporteAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.LabReporteAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.MedicoReporteAdmin;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ReportesAdminServlet", urlPatterns = {"/administrador/Reportes/*"})
public class ReportesAdminServlet extends HttpServlet {

    private final GsonUtils<MedicoReporteAdmin> gsonMedicoReporte;
    private final GsonUtils<LabReporteAdmin> gsonLabReporte;
    private final GsonUtils<ConsultaExamenReporteAdmin> gsonConsulta;
    private final GsonUtils<PorcentajeCobro> porcentaje;
    
    public ReportesAdminServlet() {
        this.gsonMedicoReporte = new GsonUtils<>();
        this.gsonLabReporte = new GsonUtils<>();
        this.gsonConsulta = new GsonUtils<>();
        this.porcentaje = new GsonUtils<>();
    }
    
    //OPCIONES 
    //1 = Porcentaje de cobro
    // 2 = top 5 medicos
    // 3 = top 5 lab
    // 4 = consultas
    // 5 = examenes
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        int opcion = Utilidades.processPath(request, response);
        
        if(opcion == 1){
            var porcentajeC = ReportesAdmin.reportePorcentaje();
            response.setStatus(HttpServletResponse.SC_OK);
            porcentaje.sendAsJson(response, porcentajeC);
        
        }else if(opcion == 2){
            var medicos = ReportesAdmin.reporteMedicos();
            gsonMedicoReporte.sendAsJson(response, medicos);
        
        }else if(opcion == 3){
            var laboratorios = ReportesAdmin.reporteLaboratorios();
            gsonLabReporte.sendAsJson(response, laboratorios);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        int opcion = Utilidades.processPath(request, response);
        
        if(opcion == 4){
            ConsultaExamenReporteAdmin informacion = gsonConsulta.readFromJson(request, ConsultaExamenReporteAdmin.class);
            
            var consultas = ReportesAdmin.reporteConsultas(informacion);
            
            gsonConsulta.sendAsJson(response, consultas);
        
        }else if(opcion == 5){
            ConsultaExamenReporteAdmin informacion = gsonConsulta.readFromJson(request, ConsultaExamenReporteAdmin.class);
            
            var examenes = ReportesAdmin.reporteExamenes(informacion);
            
            gsonConsulta.sendAsJson(response, examenes);
        }
        
    }
    
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }


}
