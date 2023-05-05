/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador;

import com.rudyreyes.proyecto2.ipc2.backend.data.LoginBD;
import com.rudyreyes.proyecto2.ipc2.backend.data.VerficarInicioSesion;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.EspecialidadDelMedico;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "PrimeraSesionMedicoServlet", urlPatterns = {"/medico/verificar"})
public class PrimeraSesionMedicoServlet extends HttpServlet {

    private final GsonUtils<Usuario> gsonUsuario;

    public PrimeraSesionMedicoServlet() {
        this.gsonUsuario = new GsonUtils<>();
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

        if (pathInfo == null || pathInfo.equals("/")) {

            var usuario = gsonUsuario.readFromJson(request, Usuario.class);

            boolean especialidad = VerficarInicioSesion.verificarMedico(usuario);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonUsuario.sendAsJson(response, especialidad);
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
