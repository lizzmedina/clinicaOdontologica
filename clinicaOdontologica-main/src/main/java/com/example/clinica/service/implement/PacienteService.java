package com.example.clinica.service.implement;

import com.example.clinica.config.MapperConfig;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.persistence.entities.Paciente;
import com.example.clinica.persistence.repository.PacienteRepository;
import com.example.clinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.util.*;

@Service
public class PacienteService implements IPacienteService<PacienteDto> {
    @Autowired
    private MapperConfig mapper;

    @Autowired
    private PacienteRepository repository;

    private Paciente toEntity (PacienteDto dto) {
        Paciente paciente = mapper.getModelMapper().map(dto, Paciente.class);
        return paciente;
    }
    @Override
    public PacienteDto crear(PacienteDto dto) throws ResourceNotFoundException {

        Paciente paciente = repository.save(toEntity(dto));

        if (!dto.getDctoIdentidad().isBlank()) {
            dto = mapper.getModelMapper().map(paciente, PacienteDto.class);
        }else {
            throw new ResourceNotFoundException("No existe o no fue posible crear el paciente, por favor revise los datos ingresados e intente nuevamente");
        }

        return dto;
    }


    @Override
    public List<PacienteDto> encontrarTodos() {
        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.findAll();

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    @Override
    public PacienteDto obtenerPorId(int id) throws ResourceNotFoundException {

        Optional<Paciente> paciente = this.repository.findById(id);

        PacienteDto pacienteDto=null;

        if (paciente.isPresent()) {
            pacienteDto =  mapper.getModelMapper().map(paciente.get(), PacienteDto.class);

        }else {
            throw new ResourceNotFoundException("No existe o no fue posible encontrar el paciente con el id ingresado");
        }
        return  pacienteDto;
    }

    @Override
    public List<PacienteDto> obtenerPorNombre(String nombre) {

        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.buscarPacientePorNombre(nombre);

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    @Override
    public List<PacienteDto> obtenerPorApellido(String apellido) {
        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.buscarPacientePorApellido(apellido);

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    @Override
    public PacienteDto actualizar (PacienteDto pacienteDto) throws ResourceNotFoundException {

        Optional<Paciente> paciente = repository.findById(pacienteDto.getId());

        if (pacienteDto !=null ||  paciente.isEmpty()){

            paciente.get().setApellido(Objects.isNull(pacienteDto.getApellido()) ?
                    paciente.get().getApellido() : pacienteDto.getApellido());

            paciente.get().setNombre(Objects.isNull(pacienteDto.getNombre())?
                    paciente.get().getApellido(): pacienteDto.getNombre());

            paciente.get().setDctoIdentidad(Objects.isNull(pacienteDto.getDctoIdentidad()) ?
                    paciente.get().getDctoIdentidad() : pacienteDto.getDctoIdentidad());

            paciente.get().setFechaIngreso(Objects.isNull(pacienteDto.getFechaIngreso()) ?
                    paciente.get().getFechaIngreso() : pacienteDto.getFechaIngreso());

            repository.save(paciente.get());

        }else {
            throw new ResourceNotFoundException("No existe o no fue posible encontrar el paciente ingresado");
        }

        return mapper.getModelMapper().map(paciente, PacienteDto.class);
    }


    @Override
    public String borrar(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Paciente eliminado correctamente.";
        }
        else {
            throw new ResourceNotFoundException("No existe o no fue posible encontrar el paciente con el id ingresado");
        }
    }
}
