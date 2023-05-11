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
public class ConsultaReportePaciente {
    
    int idConsulta;
    int idMedico;
    BigDecimal precio;
    String informe;
    int idPaciente;
    String fechaInicial;
    String fechaFinal;
    String estado;
    int idEspecialidad;

    public ConsultaReportePaciente(int idConsulta, int idMedico, BigDecimal precio, String informe, int idPaciente, String fechaInicial, String fechaFinal, String estado, int idEspecialidad) {
        this.idConsulta = idConsulta;
        this.idMedico = idMedico;
        this.precio = precio;
        this.informe = informe;
        this.idPaciente = idPaciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
        this.idEspecialidad = idEspecialidad;
    }

    public ConsultaReportePaciente(int idConsulta, int idMedico, BigDecimal precio, String informe) {
        this.idConsulta = idConsulta;
        this.idMedico = idMedico;
        this.precio = precio;
        this.informe = informe;
    }

    public ConsultaReportePaciente(int idPaciente, String fechaInicial, String fechaFinal, int idEspecialidad) {
        this.idPaciente = idPaciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.idEspecialidad = idEspecialidad;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
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

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
    
    
    
    
    
}
