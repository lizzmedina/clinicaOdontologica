package com.example.clinica.service;

import com.example.clinica.exceptions.ResourceNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface IOdontologoService <D extends Serializable> {
    D crear(D dto) throws ResourceNotFoundException;

    List<D> encontrarTodos();
    D obtenerPorId(int id) throws ResourceNotFoundException;
    D obtenerPorMatricula(String matricula) throws ResourceNotFoundException;

    D actualizar(D dto) throws ResourceNotFoundException;

    String borrar(int id) throws ResourceNotFoundException;
}
