package com.example.clinica.service;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.AgendarTurnoDto;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.model.dto.TurnoDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface ITurnoService<D extends Serializable> {

    TurnoDto crearTurno(AgendarTurnoDto agendarTurnoDto) throws Exception;
    TurnoDto verTurnoPorId(int id) throws ResourceNotFoundException;
    List<TurnoDto> verTodosLosTurnos();

    List<TurnoDto> verTurnosPorFecha(LocalDate fecha);


}
