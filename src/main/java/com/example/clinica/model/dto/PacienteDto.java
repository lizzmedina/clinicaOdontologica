package com.example.clinica.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PacienteDto implements Serializable {

    private int id;

    private String nombre;

    private String apellido;

    private String dctoIdentidad;
    private LocalDate fechaIngreso;


    public PacienteDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDctoIdentidad() {
        return dctoIdentidad;
    }

    public void setDctoIdentidad(String dctoIdentidad) {
        this.dctoIdentidad = dctoIdentidad;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
