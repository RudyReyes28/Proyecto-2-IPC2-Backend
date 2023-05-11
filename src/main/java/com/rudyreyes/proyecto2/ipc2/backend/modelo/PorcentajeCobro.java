
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

public class PorcentajeCobro {
    int idPorcentaje;
    double porcentaje;
    String fechaActivacion;
    String fechaDesactivacion;
    int idAdmin;
    String estado;

    public PorcentajeCobro(int idPorcentaje, double porcentaje, String fechaActivacion, String fechaDesactivacion, int idAdmin, String estado) {
        this.idPorcentaje = idPorcentaje;
        this.porcentaje = porcentaje;
        this.fechaActivacion = fechaActivacion;
        this.fechaDesactivacion = fechaDesactivacion;
        this.idAdmin = idAdmin;
        this.estado = estado;
    }

    public PorcentajeCobro() {
        
    }

    public PorcentajeCobro(int idPorcentaje, double porcentaje) {
        this.idPorcentaje = idPorcentaje;
        this.porcentaje = porcentaje;
    }

    public PorcentajeCobro(double porcentaje, String fechaActivacion, String fechaDesactivacion) {
        this.porcentaje = porcentaje;
        this.fechaActivacion = fechaActivacion;
        this.fechaDesactivacion = fechaDesactivacion;
    }
    
    

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(String fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public String getFechaDesactivacion() {
        return fechaDesactivacion;
    }

    public void setFechaDesactivacion(String fechaDesactivacion) {
        this.fechaDesactivacion = fechaDesactivacion;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPorcentaje() {
        return idPorcentaje;
    }

    public void setIdPorcentaje(int idPorcentaje) {
        this.idPorcentaje = idPorcentaje;
    }
    
    
    
}
