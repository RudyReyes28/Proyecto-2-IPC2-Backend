/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes;

import java.math.BigDecimal;


public class PacienteExReporteLab {
    
    int idLaboratorio;
    String nombre;
    int cantidadExamenes;
    BigDecimal total;
    String fechaInicial;
    String fechaFinal;

    public PacienteExReporteLab(int idLaboratorio, String nombre, int cantidadExamenes, BigDecimal total, String fechaInicial, String fechaFinal) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.cantidadExamenes = cantidadExamenes;
        this.total = total;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public PacienteExReporteLab(String nombre, int cantidadExamenes, BigDecimal total) {
        this.nombre = nombre;
        this.cantidadExamenes = cantidadExamenes;
        this.total = total;
    }

    public PacienteExReporteLab(int idLaboratorio, String fechaInicial, String fechaFinal) {
        this.idLaboratorio = idLaboratorio;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadExamenes() {
        return cantidadExamenes;
    }

    public void setCantidadExamenes(int cantidadExamenes) {
        this.cantidadExamenes = cantidadExamenes;
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
