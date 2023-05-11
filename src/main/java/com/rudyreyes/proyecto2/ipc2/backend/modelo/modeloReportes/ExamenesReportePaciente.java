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
public class ExamenesReportePaciente {
    
    int idSolicitud;
    int idLaboratorio;
    int idExamenSolicitado;
    BigDecimal precio;
    int idPaciente;
    String fechaInicial;
    String fechaFinal;
    String estado;
    int idExamen;

    public ExamenesReportePaciente(int idSolicitud, int idLaboratorio, int idExamenSolicitado, BigDecimal precio, int idPaciente, String fechaInicial, String fechaFinal, String estado, int idExamen) {
        this.idSolicitud = idSolicitud;
        this.idLaboratorio = idLaboratorio;
        this.idExamenSolicitado = idExamenSolicitado;
        this.precio = precio;
        this.idPaciente = idPaciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
        this.idExamen = idExamen;
    }

    public ExamenesReportePaciente(int idSolicitud, int idLaboratorio, int idExamenSolicitado, BigDecimal precio) {
        this.idSolicitud = idSolicitud;
        this.idLaboratorio = idLaboratorio;
        this.idExamenSolicitado = idExamenSolicitado;
        this.precio = precio;
    }

    public ExamenesReportePaciente(int idPaciente, String fechaInicial, String fechaFinal, String estado, int idExamen) {
        this.idPaciente = idPaciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
        this.idExamen = idExamen;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public int getIdExamenSolicitado() {
        return idExamenSolicitado;
    }

    public void setIdExamenSolicitado(int idExamenSolicitado) {
        this.idExamenSolicitado = idExamenSolicitado;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    
}
