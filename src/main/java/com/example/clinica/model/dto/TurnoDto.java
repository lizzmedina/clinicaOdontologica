package com.example.clinica.model.dto;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Paciente;

import java.io.Serializable;
import java.time.LocalDate;


public class TurnoDto implements Serializable {

    private int id;
    private LocalDate fecha;
    private Paciente paciente;
    private Odontologo odontologo;

    public TurnoDto() {
    }

    public TurnoDto(LocalDate fecha, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente_id() {
        return paciente;
    }

    public void setPaciente_id(Paciente paciente_id) {
        this.paciente = paciente_id;
    }

    public Odontologo getOdontologo_id() {
        return odontologo;
    }

    public void setOdontologo_id(Odontologo odontologo_id) {
        this.odontologo = odontologo_id;
    }
}
