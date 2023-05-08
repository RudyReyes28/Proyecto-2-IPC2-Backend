/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_medico;

import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.RevisarExamenConsulta;
import com.rudyreyes.proyecto2.ipc2.backend.data.datamedico.VerHistorialMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Consulta;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VerHistorialMedicoServlet", urlPatterns = {"/medico/VerHistorial/*"})
public class VerHistorialMedicoServlet extends HttpServlet {

    private final GsonUtils<Consulta> gsonMedico;

    public VerHistorialMedicoServlet() {
        this.gsonMedico = new GsonUtils<>();
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            var consultas = VerHistorialMedico.historialMedico();
            response.setStatus(HttpServletResponse.SC_OK);
            gsonMedico.sendAsJson(response, consultas);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        int idExamenSolicitado = Utilidades.processPath(request, response);
        
        byte[] archivoBytes = RevisarExamenConsulta.obtenerArchivo(idExamenSolicitado);
        
        // Establecer los encabezados de respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=\"documento.pdf\"");
        
        // Escribir el archivo en la respuesta
        response.getOutputStream().write(archivoBytes);
    }
    
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

}
