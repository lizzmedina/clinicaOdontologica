package com.example.clinica.service.implement;

import ch.qos.logback.classic.Logger;
import com.example.clinica.config.MapperConfig;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.repository.OdontologoRepository;
import com.example.clinica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService<OdontologoDto> {

    @Autowired
    private MapperConfig mapper;

    @Autowired
    private ObjectMapper oMapper;

    @Autowired
    private final OdontologoRepository repository;

    public OdontologoService(OdontologoRepository repository) {
        this.repository = repository;
    }

    private Odontologo toEntity (OdontologoDto dto){
        Odontologo odontologo = mapper.getModelMapper().map(dto, Odontologo.class);
        return odontologo;

    }

    @Override
    public OdontologoDto crear(OdontologoDto dto) throws ResourceNotFoundException {

        Odontologo odontologo = repository.save(toEntity(dto));

        if (!dto.getMatricula().isBlank()) {
            dto = mapper.getModelMapper().map(odontologo, OdontologoDto.class);

        }
        else {
            throw new ResourceNotFoundException("No existe o no fue posible crear el odontologo, por favor revise los datos ingresados e intente nuevamente");
        }
        return dto;
    }

    @Override
    public List<OdontologoDto> encontrarTodos() {
        List<OdontologoDto> odontologos = new ArrayList<>();

        for(Odontologo odontologo : this.repository.findAll()) {
            odontologos.add(mapper.getModelMapper().map(odontologo, OdontologoDto.class));
        }

        return odontologos;
    }

    @Override
    public OdontologoDto obtenerPorId(int id) throws ResourceNotFoundException {

        Optional<Odontologo> odontologo = this.repository.findById(id);

        OdontologoDto odontologoDto=null;

        if (odontologo.isPresent()) {
            odontologoDto =  mapper.getModelMapper().map(odontologo.get(), OdontologoDto.class);

        }
        else {
            throw new ResourceNotFoundException("No existe o no fue posible encontrar el odontologo con el id indicado.");
        }
        return  odontologoDto;

    }

    @Override
    public OdontologoDto obtenerPorMatricula(String matricula) throws ResourceNotFoundException {
        OdontologoDto resultado = null;

        Odontologo entidad = repository.buscarOdontologoPorMatricula(matricula);

        if(entidad !=null) {
            resultado = mapper.getModelMapper().map(entidad, OdontologoDto.class);
        }
        else {
            throw new ResourceNotFoundException("No existe o no fue posible encontrar el odontologo con la matricula indicada.");
        }

        return resultado;
    }


    @Override
    public OdontologoDto actualizar (OdontologoDto odontologoDto) throws ResourceNotFoundException {

        Optional<Odontologo> odontologo = repository.findById(odontologoDto.getId());

        if (odontologoDto !=null ||  odontologo.isEmpty()){

        odontologo.get().setApellido(Objects.isNull(odontologoDto.getApellido()) ?
                odontologo.get().getApellido() : odontologoDto.getApellido());

        odontologo.get().setNombre(Objects.isNull(odontologoDto.getNombre())?
                odontologo.get().getApellido(): odontologoDto.getNombre());

        odontologo.get().setMatricula(Objects.isNull(odontologoDto.getMatricula()) ?
                odontologo.get().getMatricula() : odontologoDto.getMatricula());

        repository.save(odontologo.get());

        }else {
            throw new ResourceNotFoundException("No existe o no fue posible actualizar el odontologo ingresado");
        }

        return mapper.getModelMapper().map(odontologo, OdontologoDto.class);
    }


    @Override
    public String borrar(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Odontologo eliminado correctamente.";
        }
        else {
            throw new ResourceNotFoundException("No existe o no fue posible crear el odontologo, por favor revise los datos ingresados e intente nuevamente");
        }
    }

}
