/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_paciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.reportes.ReportesPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.RecargaPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.ConsultaReportePaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.ExamenesReportePaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes.HistorialMedicoReportePaciente;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReportesPacienteServlet", urlPatterns = {"/paciente/reportes/*"})
public class ReportesPacienteServlet extends HttpServlet {

    
    private final GsonUtils<RecargaPaciente> gsonRecarga;
    private final GsonUtils<HistorialMedicoReportePaciente> gsonHistorial;
    private final GsonUtils<ConsultaReportePaciente> gsonConsulta;
    private final GsonUtils<ExamenesReportePaciente> gsonExamenes;
    
    public ReportesPacienteServlet() {
        this.gsonRecarga = new GsonUtils<>();
        this.gsonHistorial = new GsonUtils<>();
        this.gsonConsulta = new GsonUtils<>();
        this.gsonExamenes = new GsonUtils<>();
    }
    
    //1 HISTORIAL MEDICO
    //2 Recargas
    //3 Consultas
    //4 Examenes

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
            HistorialMedicoReportePaciente informacion = gsonHistorial.readFromJson(request, HistorialMedicoReportePaciente.class);
            
            var historial = ReportesPaciente.historialMedico(informacion);
            
            gsonHistorial.sendAsJson(response, historial);
        
        }else if(opcion == 2){
            RecargaPaciente informacion = gsonRecarga.readFromJson(request, RecargaPaciente.class);
            
            var recargas = ReportesPaciente.reporteRecargas(informacion);
            
            gsonRecarga.sendAsJson(response, recargas);
        
        }else if(opcion == 3){
            ConsultaReportePaciente informacion = gsonConsulta.readFromJson(request, ConsultaReportePaciente.class);
            
            var consultas = ReportesPaciente.reporteConsultasP(informacion);
            
            gsonConsulta.sendAsJson(response, consultas);
        
        }else if(opcion == 4){
            ExamenesReportePaciente informacion = gsonExamenes.readFromJson(request, ExamenesReportePaciente.class);
            
            var examenes = ReportesPaciente.reporteExamenesP(informacion);
            
            gsonExamenes.sendAsJson(response, examenes);
        }
        
    }
    
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

}
