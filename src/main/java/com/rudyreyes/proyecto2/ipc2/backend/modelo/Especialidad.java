
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

public class Especialidad {
    int idEspecialidad;
    String nombre;
    String descripcion;
    String estado;
    int idMedico;

    public Especialidad() {
    }

    public Especialidad(int idEspecialidad, String nombre, String descripcion, String estado, int idMedico) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idMedico = idMedico;
    }

    public Especialidad(int idEspecialidad, String nombre, String descripcion) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    
    

    
}
