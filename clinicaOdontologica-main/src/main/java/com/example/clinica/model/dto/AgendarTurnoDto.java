package com.example.clinica.model.dto;

import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Paciente;

import java.time.LocalDate;

public class AgendarTurnoDto {
    private int id;
    private LocalDate fecha;
    private int idPaciente;
    private int idOdontologo;

    public AgendarTurnoDto() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(int idOdontologo) {
        this.idOdontologo = idOdontologo;
    }
}
