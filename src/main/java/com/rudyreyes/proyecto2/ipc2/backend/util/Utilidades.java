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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author rudy-reyes
 */
public class Utilidades {

    public static String horaActual() {
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

    public static String[] separarFechaHora(String fechaHora) {
        // Separar la fecha y hora en dos partes
        String[] partes = fechaHora.split(" ");

        // Retornar un arreglo con la fecha y la hora separadas
        return new String[]{partes[0], partes[1]};
    }

    public static String obtenerHoraFinal(String horaInicial) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaInicialObj = LocalTime.parse(horaInicial, formatter);

        LocalTime horaFinalObj = horaInicialObj.plusHours(1);
        String horaFinal = horaFinalObj.format(formatter);

        return horaFinal;
    }

    public static String[] separarHoras(String horas) {
        // Separar la cadena en hora inicial y hora final
        String[] partes = horas.split("-");

        // Eliminar espacios en blanco al inicio y al final de cada parte
        String horaInicial = partes[0].trim();
        String horaFinal = partes[1].trim();

        // Devolver un arreglo con las dos partes
        return new String[]{horaInicial, horaFinal};
    }

    public static int reducirCui(String cui) {
        
        int nuevoCui = 0;
        if (cui.length() < 8) {
            
            nuevoCui = Integer.parseInt(cui);
        } else {
            String reducir = cui.substring(0, 8);
            
            nuevoCui = Integer.parseInt(reducir);
        }
        return nuevoCui;
    }

    
    
}
