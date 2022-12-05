package com.example.clinica.service;

import java.io.Serializable;
import java.util.List;

public interface IPacienteService<D extends Serializable> {
    D crear(D dto) ;

    List<D> encontrarTodos();
    D obtenerPorId(int id);
    D obtenerPorNombreOApellido(String nombre);

    D actualizar(D dto) throws ServiceException;

    String borrar(int id);
}
