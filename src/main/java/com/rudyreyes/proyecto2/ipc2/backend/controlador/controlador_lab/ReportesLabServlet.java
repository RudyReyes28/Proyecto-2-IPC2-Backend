/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_lab;

import com.rudyreyes.proyecto2.ipc2.backend.data.reportes.ReportesLaboratorio;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.PacienteExReporteLab;
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
@WebServlet(name = "ReportesLabServlet", urlPatterns = {"/laboratorio/reportes/*"})
public class ReportesLabServlet extends HttpServlet {

    private final GsonUtils<PacienteExReporteLab> gsonLabReporte;
    
    public ReportesLabServlet() {
        this.gsonLabReporte = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        int opcion = Utilidades.processPath(request, response);
        
        if(opcion == 1){
            PacienteExReporteLab informacion = gsonLabReporte.readFromJson(request, PacienteExReporteLab.class);
            
            var pacientes = ReportesLaboratorio.reportesPaciente(informacion);
            
            gsonLabReporte.sendAsJson(response, pacientes);
        
        }else if(opcion == 2){
            PacienteExReporteLab informacion = gsonLabReporte.readFromJson(request, PacienteExReporteLab.class);
            
            var especialidades = ReportesLaboratorio.reportesExamenes(informacion);
            
            gsonLabReporte.sendAsJson(response, especialidades);
        }
        
    }

    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

}
