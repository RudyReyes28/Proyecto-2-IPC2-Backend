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
public class LabReporteAdmin {
    
    String nombreLab;
    int cantExamenes;
    BigDecimal totalExamenes;
    BigDecimal totalApp;

    public LabReporteAdmin(String nombreLab, int cantExamenes, BigDecimal totalExamenes, BigDecimal totalApp) {
        this.nombreLab = nombreLab;
        this.cantExamenes = cantExamenes;
        this.totalExamenes = totalExamenes;
        this.totalApp = totalApp;
    }

    public String getNombreLab() {
        return nombreLab;
    }

    public void setNombreLab(String nombreLab) {
        this.nombreLab = nombreLab;
    }

    public int getCantExamenes() {
        return cantExamenes;
    }

    public void setCantExamenes(int cantExamenes) {
        this.cantExamenes = cantExamenes;
    }

    public BigDecimal getTotalExamenes() {
        return totalExamenes;
    }

    public void setTotalExamenes(BigDecimal totalExamenes) {
        this.totalExamenes = totalExamenes;
    }

    public BigDecimal getTotalApp() {
        return totalApp;
    }

    public void setTotalApp(BigDecimal totalApp) {
        this.totalApp = totalApp;
    }
    
    
}
