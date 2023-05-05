/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_admin;

import com.rudyreyes.proyecto2.ipc2.backend.data.ObtenerEspecialidades;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RegistrarEspecialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Especialidad;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RevisionEspecialidadServlet", urlPatterns = {"/administrador/RevisionEspecialidad"})
public class RevisionEspecialidadServlet extends HttpServlet {

    private final GsonUtils<Especialidad> gsonAdmin;

    public RevisionEspecialidadServlet() {
        this.gsonAdmin = new GsonUtils<>();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

            var especialidades = ObtenerEspecialidades.getEspecialidades("PENDIENTE");

            response.setStatus(HttpServletResponse.SC_OK);
            gsonAdmin.sendAsJson(response, especialidades);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        System.out.println("Si se llama el post");

        if (pathInfo == null || pathInfo.equals("/")) {

            Especialidad especialidad = gsonAdmin.readFromJson(request, Especialidad.class);

            RegistrarEspecialidad.aceptarEspecialidad(especialidad);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonAdmin.sendAsJson(response, especialidad);
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
