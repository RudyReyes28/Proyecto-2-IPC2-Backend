/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes;

import java.math.BigDecimal;


public class MedicoReporteAdmin {
    
    String nombreMedico;
    int cantConsultas;
    BigDecimal totalConsultas;
    BigDecimal totalApp;

    public MedicoReporteAdmin(String nombreMedico, int cantConsultas, BigDecimal totalConsultas, BigDecimal totalApp) {
        this.nombreMedico = nombreMedico;
        this.cantConsultas = cantConsultas;
        this.totalConsultas = totalConsultas;
        this.totalApp = totalApp;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public int getCantConsultas() {
        return cantConsultas;
    }

    public void setCantConsultas(int cantConsultas) {
        this.cantConsultas = cantConsultas;
    }

    public BigDecimal getTotalConsultas() {
        return totalConsultas;
    }

    public void setTotalConsultas(BigDecimal totalConsultas) {
        this.totalConsultas = totalConsultas;
    }

    public BigDecimal getTotalApp() {
        return totalApp;
    }

    public void setTotalApp(BigDecimal totalApp) {
        this.totalApp = totalApp;
    }
    
    
    
}
