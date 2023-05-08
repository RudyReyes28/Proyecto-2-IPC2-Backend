/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_paciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.ObtenerExamenesSolicitados;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VerResultadosExamenesServlet", urlPatterns = {"/paciente/VerResultados/*"})
public class VerResultadosExamenesServlet extends HttpServlet {

    private final GsonUtils<SolicitudExamen> gsonPaciente;

    public VerResultadosExamenesServlet() {
        this.gsonPaciente = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        
        int idPaciente = Utilidades.processPath(request, response);
        var examenes = ObtenerExamenesSolicitados.solicitudesExamenFinalizados(idPaciente);
        response.setStatus(HttpServletResponse.SC_OK);
        gsonPaciente.sendAsJson(response, examenes);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        
        int idExamenSolicitado = Utilidades.processPath(request, response);
        
        byte[] archivoBytes = ObtenerExamenesSolicitados.obtenerArchivo(idExamenSolicitado);
        
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
