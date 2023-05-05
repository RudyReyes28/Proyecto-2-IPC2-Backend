/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;

/**
 *
 * @author rudy-reyes
 */
public class Consulta {
    
    int idConsulta;
    int idPaciente;
    int idMedico;
    int idEspecialidad;
    double porcentajeApp;
    String fechaCreacion;
    BigDecimal precio;
    String informe;
    String estado;
    FechaConsulta fechaAgendada;

    public Consulta(int idConsulta, int idPaciente, int idMedico, int idEspecialidad, double porcentajeApp, String fechaCreacion, BigDecimal precio, String informe, String estado, FechaConsulta fechaAgendada) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.porcentajeApp = porcentajeApp;
        this.fechaCreacion = fechaCreacion;
        this.precio = precio;
        this.informe = informe;
        this.estado = estado;
        this.fechaAgendada = fechaAgendada;
    }

    public Consulta() {
    }

    public Consulta(int idPaciente, int idMedico, int idEspecialidad, BigDecimal precio, FechaConsulta fechaAgendada) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.precio = precio;
        this.fechaAgendada = fechaAgendada;
    }

    
    
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public double getPorcentajeApp() {
        return porcentajeApp;
    }

    public void setPorcentajeApp(double porcentajeApp) {
        this.porcentajeApp = porcentajeApp;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public FechaConsulta getFechaAgendada() {
        return fechaAgendada;
    }

    public void setFechaAgendada(FechaConsulta fechaAgendada) {
        this.fechaAgendada = fechaAgendada;
    }
    
    
    
}
