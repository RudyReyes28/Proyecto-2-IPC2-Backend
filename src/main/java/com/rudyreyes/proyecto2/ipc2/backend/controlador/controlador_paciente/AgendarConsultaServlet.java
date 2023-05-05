/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_paciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.LoginBD;
import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.AgendarConsulta;
import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.ObtenerPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.FechaConsulta;
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
@WebServlet(name = "AgendarConsultaServlet", urlPatterns = {"/paciente/AgendarConsulta/*"})
public class AgendarConsultaServlet extends HttpServlet {

    private final GsonUtils<Consulta> gsonPaciente;
    private final GsonUtils<FechaConsulta> gsonPacienteFecha;

    public AgendarConsultaServlet() {
        this.gsonPaciente = new GsonUtils<>();
        this.gsonPacienteFecha = new GsonUtils<>();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {

            var medicos = AgendarConsulta.obtenerMedicos();
            
            response.setStatus(HttpServletResponse.SC_OK);
            
            gsonPaciente.sendAsJson(response, medicos);
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

            Consulta informacion = gsonPaciente.readFromJson(request, Consulta.class);

            AgendarConsulta.agendarConsulta(informacion);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonPaciente.sendAsJson(response, informacion);
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

            FechaConsulta informacion = gsonPacienteFecha.readFromJson(request, FechaConsulta.class);

            var horarios = AgendarConsulta.obtenerHorarios(informacion);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonPaciente.sendAsJson(response, horarios);
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
