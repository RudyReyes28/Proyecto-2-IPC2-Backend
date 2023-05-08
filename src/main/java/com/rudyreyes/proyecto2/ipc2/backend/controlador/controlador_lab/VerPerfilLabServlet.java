/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador.controlador_lab;

import com.rudyreyes.proyecto2.ipc2.backend.data.ObtenerUsuarios;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
import com.rudyreyes.proyecto2.ipc2.backend.util.GsonUtils;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VerPerfilLabServlet", urlPatterns = {"/laboratorio/VerPerfil/*"})
public class VerPerfilLabServlet extends HttpServlet {

    private final GsonUtils<Usuario> gsonUsuario;

    public VerPerfilLabServlet() {
        this.gsonUsuario = new GsonUtils<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        
        int idUsuario = Utilidades.processPath(request, response);
        var usuario = ObtenerUsuarios.obtenerLaboratorio(idUsuario);
            
        response.setStatus(HttpServletResponse.SC_OK);
        gsonUsuario.sendAsJson(response, usuario);
    }

    
    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
    }
}
