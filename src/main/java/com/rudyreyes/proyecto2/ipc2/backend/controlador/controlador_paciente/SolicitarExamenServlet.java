/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_paciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.ObtenerPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.SolicitarExamenes;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
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
@WebServlet(name = "SolicitarExamenServlet", urlPatterns = {"/paciente/SolicitarExamen/*"})
public class SolicitarExamenServlet extends HttpServlet {

    private final GsonUtils<SolicitudExamen> gsonPaciente;

    public SolicitarExamenServlet() {
        this.gsonPaciente = new GsonUtils<>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {

            var laboratorios = SolicitarExamenes.obtenerLaboratorios();
            
            response.setStatus(HttpServletResponse.SC_OK);
            
            gsonPaciente.sendAsJson(response, laboratorios);
            return;
        }
        
        int idPaciente = Utilidades.processPath(request, response);
        var paciente = ObtenerPaciente.obtenerPaciente(idPaciente);
        response.setStatus(HttpServletResponse.SC_OK);
        gsonPaciente.sendAsJson(response, paciente);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            SolicitudExamen informacion = gsonPaciente.readFromJson(request, SolicitudExamen.class);

            SolicitarExamenes.solicitarExamenes(informacion);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonPaciente.sendAsJson(response, informacion);
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
