/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes;

import java.math.BigDecimal;


public class PacienteEspReporteMedico {
    
    int idMedico;
    String nombre;
    int cantidadConsultas;
    BigDecimal total;
    String fechaInicial;
    String fechaFinal;

    public PacienteEspReporteMedico(int idMedico, String nombre, int cantidadConsultas, BigDecimal total, String fechaInicial, String fechaFinal) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.cantidadConsultas = cantidadConsultas;
        this.total = total;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public PacienteEspReporteMedico(String nombre, int cantidadConsultas, BigDecimal total) {
        this.nombre = nombre;
        this.cantidadConsultas = cantidadConsultas;
        this.total = total;
    }

    
    
    public PacienteEspReporteMedico(int idMedico, String fechaInicial, String fechaFinal) {
        this.idMedico = idMedico;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(int cantidadConsultas) {
        this.cantidadConsultas = cantidadConsultas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
    
    
    
    
    
    
}
