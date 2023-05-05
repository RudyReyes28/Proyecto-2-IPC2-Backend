/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_admin;

import com.rudyreyes.proyecto2.ipc2.backend.data.ObtenerTipoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.data.datalab.RegistrarNuevoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "RevisionExamenServlet", urlPatterns = {"/administrador/RevisionExamen"})
public class RevisionExamenServlet extends HttpServlet {

     private final GsonUtils<TipoExamen> gsonAdmin;

    public RevisionExamenServlet(){
        this.gsonAdmin = new GsonUtils<>();
    }
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

            var examenes = ObtenerTipoExamen.getTipos("PENDIENTE");

            response.setStatus(HttpServletResponse.SC_OK);
            gsonAdmin.sendAsJson(response, examenes);
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

            TipoExamen examen = gsonAdmin.readFromJson(request, TipoExamen.class);

            RegistrarNuevoExamen.aceptarExamen(examen);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonAdmin.sendAsJson(response, examen);
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
