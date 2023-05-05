
package com.rudyreyes.proyecto2.ipc2.backend.modelo;


public class InfoInicialMedico {
    
    int idMedico;
    EspecialidadDelMedico especialidades [];
    HorarioMedico horarios [];

    public InfoInicialMedico(int idMedico, EspecialidadDelMedico[] especialidades, HorarioMedico[] horarios) {
        this.idMedico = idMedico;
        this.especialidades = especialidades;
        this.horarios = horarios;
    }

    public InfoInicialMedico() {
    }
    
    

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public EspecialidadDelMedico[] getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(EspecialidadDelMedico[] especialidades) {
        this.especialidades = especialidades;
    }

    public HorarioMedico[] getHorarios() {
        return horarios;
    }

    public void setHorarios(HorarioMedico[] horarios) {
        this.horarios = horarios;
    }

    
}
