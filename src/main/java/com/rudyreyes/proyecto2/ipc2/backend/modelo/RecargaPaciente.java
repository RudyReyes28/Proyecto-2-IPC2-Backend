
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;


public class RecargaPaciente {
    
    int idRecarga;
    int idPaciente;
    String fecha;
    BigDecimal monto;

    public RecargaPaciente(int idRecarga, int idPaciente, String fecha, BigDecimal monto) {
        this.idRecarga = idRecarga;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.monto = monto;
    }

    public RecargaPaciente(int idRecarga, String fecha, BigDecimal monto) {
        this.idRecarga = idRecarga;
        this.fecha = fecha;
        this.monto = monto;
    }
    
    

    public RecargaPaciente() {
    }

    public int getIdRecarga() {
        return idRecarga;
    }

    public void setIdRecarga(int idRecarga) {
        this.idRecarga = idRecarga;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    
    
}
