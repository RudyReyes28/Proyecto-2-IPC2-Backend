/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_medico;

import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.AgregarEspecialidad;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ActualizarEspecialidadServlet", urlPatterns = {"/medico/ActualizarEspecialidad/*"})
public class ActualizarEspecialidadServlet extends HttpServlet {

  private final GsonUtils<EspecialidadDelMedico> gsonMedico;

    public ActualizarEspecialidadServlet() {
        this.gsonMedico = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        doOptions(request, response);

        int idMedico = Utilidades.processPath(request, response);

        var especialidades = AgregarEspecialidad.getEspecialidadesMedico(idMedico);

        response.setStatus(HttpServletResponse.SC_OK);
        gsonMedico.sendAsJson(response, especialidades);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        System.out.println("Si se llama el post");

        if (pathInfo == null || pathInfo.equals("/")) {

            EspecialidadDelMedico informacion = gsonMedico.readFromJson(request, EspecialidadDelMedico.class);

            AgregarEspecialidad.registrarEspecialidad(informacion);
            
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
        System.out.println("Si se llama el post");

        if (pathInfo == null || pathInfo.equals("/")) {

            EspecialidadDelMedico especialidad = gsonMedico.readFromJson(request, EspecialidadDelMedico.class);

            AgregarEspecialidad.modificarEspecialidad(especialidad);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonMedico.sendAsJson(response, especialidad);
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
