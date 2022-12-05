package com.example.clinica.service.implement;

import com.example.clinica.config.MapperConfig;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.repository.OdontologoRepository;
import com.example.clinica.service.IOdontologoService;
import com.example.clinica.service.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
   /* private void guardar (OdontologoDto odontologoDto){
        Odontologo odontologo = mapper.getModelMapper().map(odontologoDto, Odontologo.class);
        repository.save(odontologo);
    }*/
    @Override
    public OdontologoDto crear(OdontologoDto dto) {

        Odontologo odontologo = repository.save(toEntity(dto));

        if (odontologo != null) {
            dto = mapper.getModelMapper().map(odontologo, OdontologoDto.class);
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
    public OdontologoDto obtenerPorId(int id) {

        Optional<Odontologo> odontologo = this.repository.findById(id);

        OdontologoDto odontologoDto=null;

        if (odontologo.isPresent()) {
            odontologoDto =  mapper.getModelMapper().map(odontologo.get(), OdontologoDto.class);

        }
        return  odontologoDto;

    }

    @Override
    public OdontologoDto obtenerPorMatricula(String matricula) {
        return null;
    }

    @Override
    public OdontologoDto actualizar (OdontologoDto odontologoDto) throws ServiceException {

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
            throw new ServiceException("No existe o no fue posible encontrar el odontologo ingresado");
        }

        return mapper.getModelMapper().map(odontologo, OdontologoDto.class);
    }


    @Override
    public String borrar(int id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Odontologo eliminado correctamente.";
        }
        return "Error! El id de odontologo digitado no existe";
    }

}
