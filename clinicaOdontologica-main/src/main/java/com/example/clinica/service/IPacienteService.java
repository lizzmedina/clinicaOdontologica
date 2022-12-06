package com.example.clinica.service;

import com.example.clinica.model.dto.OdontologoDto;

import java.io.Serializable;
import java.util.List;

public interface IPacienteService<D extends Serializable> {
    D crear(D dto) ;

    List<D> encontrarTodos();
    D obtenerPorId(int id);
    List<D> obtenerPorNombre(String nombre);
    List<D> obtenerPorApellido(String nombre);

    D actualizar(D dto) throws ServiceException;

    String borrar(int id);
}
