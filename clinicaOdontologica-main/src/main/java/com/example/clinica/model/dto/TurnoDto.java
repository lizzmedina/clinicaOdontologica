package com.example.clinica.model.dto;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Paciente;

import java.io.Serializable;
import java.time.LocalDate;


public class TurnoDto implements Serializable {

    private int id;
    private LocalDate fecha;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

    public TurnoDto() {
    }

    public TurnoDto(LocalDate fecha, PacienteDto paciente, OdontologoDto odontologo) {
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

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }
}
