/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_admin;

import com.rudyreyes.proyecto2.ipc2.backend.data.ActualizarPorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.PorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CambiarPorcentajeServlet", urlPatterns = {"/administrador/PorcentajeCobro"})
public class CambiarPorcentajeServlet extends HttpServlet {

    private final GsonUtils<PorcentajeCobro> gsonAdmin;

    public CambiarPorcentajeServlet() {
        this.gsonAdmin = new GsonUtils<>();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

            var porcentaje = ActualizarPorcentajeCobro.obtenerPorcentaje();

            response.setStatus(HttpServletResponse.SC_OK);
            gsonAdmin.sendAsJson(response, porcentaje);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        System.out.println("Si se llama el post");

        if (pathInfo == null || pathInfo.equals("/")) {

            PorcentajeCobro porcentaje = gsonAdmin.readFromJson(request, PorcentajeCobro.class);

            ActualizarPorcentajeCobro.desactivarPorcentaje();
            ActualizarPorcentajeCobro.agregarPorcentaje(porcentaje);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonAdmin.sendAsJson(response, porcentaje);
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
