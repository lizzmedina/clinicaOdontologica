package com.example.clinica.service;

import java.io.Serializable;
import java.util.List;

public interface IOdontologoService <D extends Serializable> {
    D crear(D dto) ;

    List<D> encontrarTodos();
    D obtenerPorId(int id);
    List<D> obtenerPorMatricula(String matricula);

    D actualizar(D dto) throws ServiceException;

    String borrar(int id);
}
