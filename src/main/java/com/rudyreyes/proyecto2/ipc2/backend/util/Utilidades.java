/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author rudy-reyes
 */
public class Utilidades {
    
    public static String horaActual(){
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraActual = fechaHora.format(formatter);
        
        return fechaHoraActual;
    }
    
    public static int processPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String httpMethod = request.getMethod();

        if (httpMethod.equals("PUT") || httpMethod.equals("DELETE")) {
            if (pathInfo == null || pathInfo.equals("/")) {

                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return -1;
            }
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }

        String idUsuario = splits[1];

        try {
            Integer.parseInt(idUsuario);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }

        return Integer.parseInt(idUsuario);
    }
    
    public static String obtenerFechaActual() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActualStr = formatoFecha.format(fechaActual);
        return fechaActualStr;
    }
}
