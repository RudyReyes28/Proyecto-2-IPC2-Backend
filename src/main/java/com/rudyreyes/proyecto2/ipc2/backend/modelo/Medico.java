/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;
import java.util.List;

public class Medico {
    
    int idMedico;
    String nombre;
    String usuario;
    BigDecimal saldo;
    List <EspecialidadDelMedico> especialidad;

    public Medico(int idMedico, String nombre, String usuario, BigDecimal saldo, List<EspecialidadDelMedico> especialidad) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.usuario = usuario;
        this.saldo = saldo;
        this.especialidad = especialidad;
    }

    
    
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<EspecialidadDelMedico> getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(List<EspecialidadDelMedico> especialidad) {
        this.especialidad = especialidad;
    }

    
    
    
}
