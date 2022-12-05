package com.example.clinica.service.implement;

import com.example.clinica.config.MapperConfig;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.persistence.entities.Paciente;
import com.example.clinica.persistence.repository.PacienteRepository;
import com.example.clinica.service.IPacienteService;
import com.example.clinica.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PacienteDto crear(PacienteDto dto) {

        Paciente paciente = repository.save(toEntity(dto));

        if (paciente != null) {
            dto = mapper.getModelMapper().map(paciente, PacienteDto.class);
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
    public PacienteDto obtenerPorId(int id) {

        Optional<Paciente> paciente = this.repository.findById(id);

        PacienteDto pacienteDto=null;

        if (paciente.isPresent()) {
            pacienteDto =  mapper.getModelMapper().map(paciente.get(), PacienteDto.class);

        }
        return  pacienteDto;
    }

    @Override
    public PacienteDto obtenerPorNombreOApellido(String valor) {

      Optional<Paciente> paciente = repository.buscarPaciente(valor);

        PacienteDto  pacienteDto = (mapper.getModelMapper().map(paciente.get(), PacienteDto.class));


        return pacienteDto;
    }



    @Override
    public PacienteDto actualizar (PacienteDto pacienteDto) throws ServiceException {

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
            throw new ServiceException("No existe o no fue posible encontrar el paciente ingresado");
        }

        return mapper.getModelMapper().map(paciente, PacienteDto.class);
    }


    @Override
    public String borrar(int id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Paciente eliminado correctamente.";
        }
        return "Error! El id del paciente digitado no existe";
    }
}
