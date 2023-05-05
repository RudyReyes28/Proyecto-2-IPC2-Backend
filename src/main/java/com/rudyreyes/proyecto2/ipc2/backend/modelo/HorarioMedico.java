/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto2.ipc2.backend.modelo;


public class HorarioMedico {
    String horaInicial;
    String horaFinal;
    int idMedico;
    int idHorario;

    public HorarioMedico(String horaInicial, String horaFinal, int idMedico, int idHorario) {
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.idMedico = idMedico;
        this.idHorario = idHorario;
    }
    
    public HorarioMedico(String horaInicial, String horaFinal) {
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }

    public HorarioMedico() {
    }

    
    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horarioFinal) {
        this.horaFinal = horarioFinal;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
    
    
    
}
