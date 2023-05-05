/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador;

import com.rudyreyes.proyecto2.ipc2.backend.data.ObtenerTipoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.data.RegistrarInfoInicialLab;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.InfoInicialLab;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "InfoInicialLabServlet", urlPatterns = {"/laboratorio/InformacionInicial"})
public class InfoInicialLabServlet extends HttpServlet {

    private final GsonUtils<InfoInicialLab> gsonLab;
    private final GsonUtils<String> gsonExamen;

    public InfoInicialLabServlet() {
        this.gsonLab = new GsonUtils<>();
        this.gsonExamen = new GsonUtils<>();
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

            List<TipoExamen> tipos = ObtenerTipoExamen.getTipos("ACEPTADA");

            response.setStatus(HttpServletResponse.SC_OK);
            gsonExamen.sendAsJson(response, tipos);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            InfoInicialLab informacion = gsonLab.readFromJson(request, InfoInicialLab.class);

            RegistrarInfoInicialLab.registrarInformacion(informacion);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonLab.sendAsJson(response, informacion);
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
