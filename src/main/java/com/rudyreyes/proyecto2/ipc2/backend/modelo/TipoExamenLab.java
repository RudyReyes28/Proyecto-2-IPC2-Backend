
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;

public class TipoExamenLab {
    int idTipo;
    BigDecimal precio;
    int idLaboratorio;
    
    
    public TipoExamenLab(int idTipo, BigDecimal precio) {
        this.idTipo = idTipo;
        this.precio = precio;
    }

    public TipoExamenLab(int idTipo, BigDecimal precio, int idLaboratorio) {
        this.idTipo = idTipo;
        this.precio = precio;
        this.idLaboratorio = idLaboratorio;
    }
    
    

    public TipoExamenLab() {
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }
    
    
    
}
