/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_paciente;

import com.rudyreyes.proyecto2.ipc2.backend.data.ActualizarPorcentajeCobro;
import com.rudyreyes.proyecto2.ipc2.backend.data.datapaciente.RegistrarRecarga;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.RecargaPaciente;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RealizarRecargaServlet", urlPatterns = {"/paciente/Recarga"})
public class RealizarRecargaServlet extends HttpServlet {

    private final GsonUtils<RecargaPaciente> gsonAdmin;

    public RealizarRecargaServlet() {
        this.gsonAdmin = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doOptions(request, response);
        String pathInfo = request.getPathInfo();
        System.out.println("Si se llama el post");

        if (pathInfo == null || pathInfo.equals("/")) {

            RecargaPaciente recarga = gsonAdmin.readFromJson(request, RecargaPaciente.class);

            
            RegistrarRecarga.realizarRecarga(recarga);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonAdmin.sendAsJson(response, recarga);
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
