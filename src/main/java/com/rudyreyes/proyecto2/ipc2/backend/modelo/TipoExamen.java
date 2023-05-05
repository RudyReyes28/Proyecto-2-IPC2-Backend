
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

public class TipoExamen {
    int idTipo;
    String nombre;
    String descripcion;
    String estado;
    int idLaboratorio;

    public TipoExamen() {
    }

    public TipoExamen(int idTipo, String nombre, String descripcion, String estado, int idLaboratorio) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idLaboratorio = idLaboratorio;
    }

    public TipoExamen(int idTipo, String nombre, String descripcion) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    
    
    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }
    
    
}
