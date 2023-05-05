
package com.rudyreyes.proyecto2.ipc2.backend.controlador;

import com.rudyreyes.proyecto2.ipc2.backend.data.Conexion;
import com.rudyreyes.proyecto2.ipc2.backend.data.LoginBD;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.Usuario;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final GsonUtils<Usuario> gsonUsuario;

    public LoginServlet() {
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

            Usuario usuarioRegistrado = LoginBD.validarUsuario(usuario);
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonUsuario.sendAsJson(response, usuarioRegistrado);
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
