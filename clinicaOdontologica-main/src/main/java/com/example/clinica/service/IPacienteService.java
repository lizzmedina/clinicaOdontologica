package com.example.clinica.service;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.OdontologoDto;

import java.io.Serializable;
import java.util.List;

public interface IPacienteService<D extends Serializable> {
    D crear(D dto) throws ResourceNotFoundException;

    List<D> encontrarTodos();
    D obtenerPorId(int id) throws ResourceNotFoundException;
    List<D> obtenerPorNombre(String nombre);
    List<D> obtenerPorApellido(String nombre);

    D actualizar(D dto) throws ResourceNotFoundException;

    String borrar(int id) throws ResourceNotFoundException;
}
