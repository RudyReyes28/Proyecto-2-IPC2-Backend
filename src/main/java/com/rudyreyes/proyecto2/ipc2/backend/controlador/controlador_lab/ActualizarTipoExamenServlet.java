/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_lab;

import com.rudyreyes.proyecto2.ipc2.backend.data.datalab.AgregarExamen;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.TipoExamenLab;
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
@WebServlet(name = "ActualizarTipoExamenServlet", urlPatterns = {"/laboratorio/ActualizarExamen/*"})
public class ActualizarTipoExamenServlet extends HttpServlet {

    private final GsonUtils<TipoExamenLab> gsonLab;

    public ActualizarTipoExamenServlet() {
        this.gsonLab = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);

        String pathInfo = request.getPathInfo();

        int idLab = Utilidades.processPath(request, response);;
        var examenes = AgregarExamen.getExamenesLab(idLab);

        response.setStatus(HttpServletResponse.SC_OK);
        gsonLab.sendAsJson(response, examenes);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        System.out.println("Si se llama el post");

        if (pathInfo == null || pathInfo.equals("/")) {

            TipoExamenLab informacion = gsonLab.readFromJson(request, TipoExamenLab.class);

            AgregarExamen.registrarExamen(informacion);

            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonLab.sendAsJson(response, informacion);
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

            TipoExamenLab especialidad = gsonLab.readFromJson(request, TipoExamenLab.class);

            AgregarExamen.modificarExamen(especialidad);

            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonLab.sendAsJson(response, especialidad);
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
