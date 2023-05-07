/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;

public class ExamenSolicitado {
    int idExamenSolicitado;
    int idSolicitud;
    int idConsulta;
    int idExamen;
    BigDecimal precio;
    String resultado;

    public ExamenSolicitado(int idExamenSolicitado, int idSolicitud, int idConsulta, int idExamen, BigDecimal precio, String resultado) {
        this.idExamenSolicitado = idExamenSolicitado;
        this.idSolicitud = idSolicitud;
        this.idConsulta = idConsulta;
        this.idExamen = idExamen;
        this.precio = precio;
        this.resultado = resultado;
    }

    public ExamenSolicitado(int idSolicitud, int idExamen, BigDecimal precio, String resultado) {
        this.idSolicitud = idSolicitud;
        this.idExamen = idExamen;
        this.precio = precio;
        this.resultado = resultado;
    }

    public ExamenSolicitado(int idConsulta, int idExamen) {
        this.idConsulta = idConsulta;
        this.idExamen = idExamen;
    }
    
    public ExamenSolicitado(int idExamenSolicitado, int idSolicitud, int idExamen) {
        this.idExamenSolicitado = idExamenSolicitado;
        this.idSolicitud = idSolicitud;
        this.idExamen = idExamen;
    }
    
    public ExamenSolicitado() {
    }
    
    

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
    
}
