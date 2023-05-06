/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_medico;

import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.VerConsultasPendientes;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "VerConsultasServlet", urlPatterns = {"/medico/VerConsultas/*"})
public class VerConsultasServlet extends HttpServlet {

    private final GsonUtils<Consulta> gsonMedico;
    private final GsonUtils<SolicitudExamen> gsonExamen;
    
    public VerConsultasServlet() {
        this.gsonMedico = new GsonUtils<>();
        this.gsonExamen = new GsonUtils<>();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        
        
        int idMedico = Utilidades.processPath(request, response);
        var consultas = VerConsultasPendientes.obtenerConsultas(idMedico);
            
        response.setStatus(HttpServletResponse.SC_OK);
        gsonMedico.sendAsJson(response, consultas);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            SolicitudExamen examenes =  gsonExamen.readFromJson(request, SolicitudExamen.class);

            VerConsultasPendientes.agregarExamenesConsulta(examenes.getExamenes());
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonExamen.sendAsJson(response, examenes);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            Consulta informacion = gsonMedico.readFromJson(request, Consulta.class);

            VerConsultasPendientes.finalizarConsulta(informacion);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonMedico.sendAsJson(response, informacion);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
    }

    @Override
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

}
