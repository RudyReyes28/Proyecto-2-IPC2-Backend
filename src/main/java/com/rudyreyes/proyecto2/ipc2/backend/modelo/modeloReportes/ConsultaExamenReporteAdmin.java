/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes;

import java.math.BigDecimal;

/**
 *
 * @author rudy-reyes
 */
public class ConsultaExamenReporteAdmin {
    
    String fechaInicial;
    String fechaFinal;
    int cantidad;
    BigDecimal totalIngresos;
    BigDecimal totalApp;

    public ConsultaExamenReporteAdmin(String fechaInicial, String fechaFinal, int cantidad, BigDecimal totalIngresos, BigDecimal totalApp) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.cantidad = cantidad;
        this.totalIngresos = totalIngresos;
        this.totalApp = totalApp;
    }

    public ConsultaExamenReporteAdmin(int cantidad, BigDecimal totalIngresos, BigDecimal totalApp) {
        this.cantidad = cantidad;
        this.totalIngresos = totalIngresos;
        this.totalApp = totalApp;
    }

    public ConsultaExamenReporteAdmin(String fechaInicial, String fechaFinal) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalApp() {
        return totalApp;
    }

    public void setTotalApp(BigDecimal totalApp) {
        this.totalApp = totalApp;
    }
    
    
}
