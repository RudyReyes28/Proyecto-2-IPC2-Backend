
package com.rudyreyes.proyecto2.ipc2.backend.modelo;

public class FechaConsulta {
    
    int idConsulta;
    String fecha;
    int idHorario;
    String horaInicial;
    String horaFinal;
    String estado;
    int idMedico;

    public FechaConsulta(int idConsulta, String fecha, int idHorario, String horaInicial, String horaFinal, String estado, int idMedico) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.idHorario = idHorario;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.estado = estado;
        this.idMedico = idMedico;
    }

    

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
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

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    
    
}
