/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.controlador;

import com.rudyreyes.proyecto2.ipc2.backend.data.leerArchivo.LeerJson;
import com.rudyreyes.proyecto2.ipc2.backend.util.Utilidades;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "LeerArchivoServlet", urlPatterns = {"/cargarDatos"})
@MultipartConfig
public class LeerArchivoServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doOptions(request, response);
        
        
        // Obtener el archivo cargado
        Part filePart = request.getPart("archivoEntrada");
        String inputFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        guardarArchivo(filePart, inputFileName);

        response.setStatus(HttpServletResponse.SC_CREATED);
        
        
    }
    
    private void guardarArchivo(Part filePart, String nombreArchivo) {
        File ruta = new File("/tmp");
        File file = new File(ruta, nombreArchivo);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LeerJson.leerTodo(nombreArchivo);
            System.out.println("Archivo guardado");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

   
}
