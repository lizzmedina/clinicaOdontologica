package com.example.clinica.service.implement;

import com.example.clinica.config.MapperConfig;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.AgendarTurnoDto;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.model.dto.TurnoDto;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Paciente;
import com.example.clinica.persistence.entities.Turno;
import com.example.clinica.persistence.repository.ITurnoRepository;
import com.example.clinica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TurnoServiceImpl implements ITurnoService<TurnoDto> {

    // DEPENDENCIES
    @Autowired
    private MapperConfig mapper;

    @Autowired
    private ITurnoRepository repository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;



    // IMPLEMENTATION

   @Override
   public TurnoDto crearTurno(AgendarTurnoDto agendarTurnoDto) throws ResourceNotFoundException {
       Turno turno = new Turno();
       TurnoDto turnoDto;
       if (!agendarTurnoDto.getFecha().isBefore(LocalDate.now())) {

           PacienteDto pacienteDto = pacienteService.obtenerPorId(agendarTurnoDto.getIdPaciente());
           if (pacienteDto ==null){
               throw new ResourceNotFoundException("No se pudo encontrar el paciente con el id indicado.");
           }


           OdontologoDto odontologoDto = odontologoService.obtenerPorId(agendarTurnoDto.getIdOdontologo());
           if (odontologoDto ==null){
               throw new ResourceNotFoundException("No se pudo encontrar el odontologo con el id indicado.");
           }

           turno.setPaciente(mapper.getModelMapper().map(pacienteDto, Paciente.class));
           turno.setOdontologo(mapper.getModelMapper().map(odontologoDto, Odontologo.class));
           turno.setFecha(agendarTurnoDto.getFecha());
           Turno turnoGuardado = guardarTurno(turno);
           turnoDto = mapper.getModelMapper().map(turnoGuardado, TurnoDto.class);
       } else
           throw new ResourceNotFoundException("La fecha ingresada es anterior a la fecha actual, " +
                   "intente con una fecha posterior.");
       return turnoDto;
   }

    @Override
    public List<TurnoDto> verTodosLosTurnos() {
        List<TurnoDto> resultado = new ArrayList<>();
        List<Turno> turno = repository.findAll();

         turno.forEach(e -> resultado.add((mapper.getModelMapper().map(e, TurnoDto.class))));


        return resultado;
    }


    @Override
    public TurnoDto verTurnoPorId(int id) throws ResourceNotFoundException {

        Optional<Turno> t = repository.findById(id);

        TurnoDto turnoDto =null;
        if (t.isPresent()) {

            turnoDto = mapper.getModelMapper().map(t.get(), TurnoDto.class);
        }
        else
            throw new ResourceNotFoundException("ERROR! No existe el id para ningun turno registrado. ");

        return  turnoDto;
    }


    @Override
    public List<TurnoDto> verTurnosPorFecha(LocalDate fecha) {
        List<TurnoDto> resultado = new ArrayList<>();
        List<Turno> etidades = repository.buscarTurnosPorFecha(fecha);
        etidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, TurnoDto.class)));
        return resultado;
    }


    // PRIVATE METHODS

    private Turno guardarTurno (Turno turno) {
        TurnoDto turnoDto = mapper.getModelMapper().map(turno, TurnoDto.class);
       repository.save(turno);
        return turno;
    }

}
