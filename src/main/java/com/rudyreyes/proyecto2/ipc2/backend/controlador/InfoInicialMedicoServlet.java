/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador;

import com.rudyreyes.proyecto2.ipc2.backend.data.ObtenerEspecialidades;
import com.rudyreyes.proyecto2.ipc2.backend.data.RegistrarInfoInicialMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.HorarioMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.InfoInicialMedico;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "InfoInicialMedicoServlet", urlPatterns = {"/medico/InformacionInicial"})
public class InfoInicialMedicoServlet extends HttpServlet {

    private final GsonUtils<InfoInicialMedico> gsonMedico;

    public InfoInicialMedicoServlet() {
        this.gsonMedico = new GsonUtils<>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

            var especialidades = ObtenerEspecialidades.getEspecialidades("ACEPTADA");

            response.setStatus(HttpServletResponse.SC_OK);
            gsonMedico.sendAsJson(response, especialidades);
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

            InfoInicialMedico informacion = gsonMedico.readFromJson(request, InfoInicialMedico.class);

            System.out.println(informacion.getIdMedico());
            RegistrarInfoInicialMedico.registrarInformacionInicial(informacion);
            
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
