
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.util.List;

public class SolicitudExamen {
    
    int idSolicitud;
    int idPaciente;
    int idLaboratorio;
    double porcentaje;
    String fecha_solicitado;
    String fecha_finalizado;
    String estado;
    List<ExamenSolicitado> examenes;

    public SolicitudExamen(int idSolicitud, int idPaciente, int idLaboratorio, double porcentaje, String fecha_solicitado, String fecha_finalizado, String estado, List<ExamenSolicitado> examenes) {
        this.idSolicitud = idSolicitud;
        this.idPaciente = idPaciente;
        this.idLaboratorio = idLaboratorio;
        this.porcentaje = porcentaje;
        this.fecha_solicitado = fecha_solicitado;
        this.fecha_finalizado = fecha_finalizado;
        this.estado = estado;
        this.examenes = examenes;
    }

    public SolicitudExamen(int idPaciente, int idLaboratorio, double porcentaje, String fecha_solicitado, String fecha_finalizado, String estado, List<ExamenSolicitado> examenes) {
        this.idPaciente = idPaciente;
        this.idLaboratorio = idLaboratorio;
        this.porcentaje = porcentaje;
        this.fecha_solicitado = fecha_solicitado;
        this.fecha_finalizado = fecha_finalizado;
        this.estado = estado;
        this.examenes = examenes;
    }

    public SolicitudExamen(int idSolicitud, int idPaciente, String fecha_solicitado, List<ExamenSolicitado> examenes) {
        this.idSolicitud = idSolicitud;
        this.idPaciente = idPaciente;
        this.fecha_solicitado = fecha_solicitado;
        this.examenes = examenes;
    }
    
    

    public SolicitudExamen() {
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFecha_solicitado() {
        return fecha_solicitado;
    }

    public void setFecha_solicitado(String fecha_solicitado) {
        this.fecha_solicitado = fecha_solicitado;
    }

    public String getFecha_finalizado() {
        return fecha_finalizado;
    }

    public void setFecha_finalizado(String fecha_finalizado) {
        this.fecha_finalizado = fecha_finalizado;
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
    
    
    
}
