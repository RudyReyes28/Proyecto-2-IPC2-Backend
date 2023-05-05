/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_medico;

import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.AgregarHorario;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.HorarioMedico;
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
@WebServlet(name = "ActualizarHorarioServlet", urlPatterns = {"/medico/ActualizarHorario/*"})
public class ActualizarHorarioServlet extends HttpServlet {

    private final GsonUtils<HorarioMedico> gsonMedico;

    public ActualizarHorarioServlet() {
        this.gsonMedico = new GsonUtils<>();
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);

        int idMedico = Utilidades.processPath(request, response);

        var horarios = AgregarHorario.getHorarios(idMedico);

        response.setStatus(HttpServletResponse.SC_OK);
        gsonMedico.sendAsJson(response, horarios);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            HorarioMedico informacion = gsonMedico.readFromJson(request, HorarioMedico.class);

            AgregarHorario.registrarHorario(informacion);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonMedico.sendAsJson(response, informacion);
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

            HorarioMedico horario = gsonMedico.readFromJson(request, HorarioMedico.class);

            AgregarHorario.modificarHorario(horario);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonMedico.sendAsJson(response, horario);
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
