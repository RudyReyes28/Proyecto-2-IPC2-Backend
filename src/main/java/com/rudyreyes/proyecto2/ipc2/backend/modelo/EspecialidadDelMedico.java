
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;

public class EspecialidadDelMedico {
    
    int id;
    BigDecimal precio;
    int idMedico;

    public EspecialidadDelMedico(int id, BigDecimal precio, int idMedico) {
        this.id = id;
        this.precio = precio;
        this.idMedico = idMedico;
    }

    public EspecialidadDelMedico(int id, BigDecimal precio) {
        this.id = id;
        this.precio = precio;
    }
    
    public EspecialidadDelMedico() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    
    
    
    
}
