package com.example.clinica.service;

import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.model.dto.TurnoDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface ITurnoService<D extends Serializable> {

    TurnoDto crearTurno(int idPaciente, int idOdontologo, LocalDate fecha) throws Exception;
    TurnoDto verTurnoPorId(int id);
    List<TurnoDto> verTodosLosTurnos();

    List<TurnoDto> verTurnosPorFecha(LocalDate fecha);


}
