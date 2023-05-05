/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

public class InfoInicialLab {
    int idLaboratorio;
    TipoExamenLab examenes [];

    public InfoInicialLab(int idLaboratorio, TipoExamenLab[] examenes) {
        this.idLaboratorio = idLaboratorio;
        this.examenes = examenes;
    }

    public InfoInicialLab() {
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public TipoExamenLab[] getExamenes() {
        return examenes;
    }

    public void setExamenes(TipoExamenLab[] examenes) {
        this.examenes = examenes;
    }
    
    
}
