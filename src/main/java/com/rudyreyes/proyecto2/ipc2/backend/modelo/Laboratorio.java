/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

import java.math.BigDecimal;
import java.util.List;

public class Laboratorio {
    
    int idLaboratorio;
    String nombre;
    String usuario;
    BigDecimal saldo;
    List<TipoExamenLab> examenes;

    public Laboratorio(int idLaboratorio, String nombre, String usuario, BigDecimal saldo, List<TipoExamenLab> examenes) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.usuario = usuario;
        this.saldo = saldo;
        this.examenes = examenes;
    }

    public Laboratorio(String nombre, String usuario, BigDecimal saldo, List<TipoExamenLab> examenes) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.saldo = saldo;
        this.examenes = examenes;
    }
    
    

    public Laboratorio() {
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<TipoExamenLab> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<TipoExamenLab> examenes) {
        this.examenes = examenes;
    }

    
    
    
    
}
