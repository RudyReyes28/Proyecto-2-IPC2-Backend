/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo.modeloReportes;

import com.rudyreyes.proyecto2.ipc2.backend.modelo.ExamenSolicitado;
import com.rudyreyes.proyecto2.ipc2.backend.modelo.FechaConsulta;
import java.math.BigDecimal;
import java.util.List;


public class HistorialMedicoReportePaciente {
    
    int idConsulta;
    int idPaciente;
    int idMedico;
    int idEspecialidad;
    BigDecimal precio;
    String informe;
    String estado;
    List<ExamenSolicitado> examenes;
    String fechaInicial;
    String fechaFinal;

    public HistorialMedicoReportePaciente(int idConsulta, int idPaciente, int idMedico, int idEspecialidad, BigDecimal precio, String informe, String estado, List<ExamenSolicitado> examenes, String fechaInicial, String fechaFinal) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.precio = precio;
        this.informe = informe;
        this.estado = estado;
        this.examenes = examenes;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public HistorialMedicoReportePaciente(int idConsulta, int idMedico, int idEspecialidad, BigDecimal precio, String informe, List<ExamenSolicitado> examenes) {
        this.idConsulta = idConsulta;
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.precio = precio;
        this.informe = informe;
        this.examenes = examenes;
    }

    public HistorialMedicoReportePaciente(int idPaciente, String fechaInicial, String fechaFinal) {
        this.idPaciente = idPaciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
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

    public List<ExamenSolicitado> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<ExamenSolicitado> examenes) {
        this.examenes = examenes;
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
