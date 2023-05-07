/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_lab;

import com.rudyreyes.proyecto2.ipc2.backend.data.datalab.SolicitudExamenesPendientes;
import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.ObtenerPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.SolicitudExamen;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "SolicitudExamenPendienteServlet", urlPatterns = {"/laboratorio/SolicitudExamenPendiente/*"})
@MultipartConfig
public class SolicitudExamenPendienteServlet extends HttpServlet {

    private final GsonUtils<SolicitudExamen> gsonLab;

    public SolicitudExamenPendienteServlet() {
        this.gsonLab = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        int idLaboratorio = Utilidades.processPath(request, response);
        var examenes = SolicitudExamenesPendientes.solicitudesPendientes(idLaboratorio);
        response.setStatus(HttpServletResponse.SC_OK);
        gsonLab.sendAsJson(response, examenes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        int idExamenSolicitado = Utilidades.processPath(request, response);
        System.out.println(idExamenSolicitado);
        
        // Obtener el archivo cargado
        Part filePart = request.getPart("examen"+idExamenSolicitado);

        // Obtener el nombre y tipo de archivo
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();

        // Obtener los datos del archivo
        InputStream fileContent = filePart.getInputStream();
        byte[] fileData = fileContent.readAllBytes();
        
        SolicitudExamenesPendientes.guardarExamen(fileData, idExamenSolicitado);
        
    }
    
    
    @Override
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

}
