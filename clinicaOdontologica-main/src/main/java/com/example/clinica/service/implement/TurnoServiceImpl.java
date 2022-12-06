package com.example.clinica.service.implement;
import com.example.clinica.config.MapperConfig;
import com.example.clinica.model.dto.AgendarTurnoDto;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.model.dto.TurnoDto;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Paciente;
import com.example.clinica.persistence.entities.Turno;
import com.example.clinica.persistence.repository.ITurnoRepository;
import com.example.clinica.service.ITurnoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
   public TurnoDto crearTurno(AgendarTurnoDto agendarTurnoDto) throws Exception {
       Turno turno = new Turno();
       TurnoDto turnoDto;
       if (!agendarTurnoDto.getFecha().isBefore(LocalDate.now())) {
           try {
               PacienteDto pacienteDto = pacienteService.obtenerPorId(agendarTurnoDto.getIdPaciente());
               turno.setPaciente(mapper.getModelMapper().map(pacienteDto, Paciente.class));
           } catch (ServiceException e) {
               throw new Exception(e.getMessage());
           }
           try {
               OdontologoDto odontologoDto = odontologoService.obtenerPorId(agendarTurnoDto.getIdOdontologo());
               turno.setOdontologo(mapper.getModelMapper().map(odontologoDto, Odontologo.class));
           } catch (ServiceException e) {
               throw new Exception(e.getMessage());
           }
           turno.setFecha(agendarTurnoDto.getFecha());
           Turno turnoGuardado = guardarTurno(turno);
           turnoDto = mapper.getModelMapper().map(turnoGuardado, TurnoDto.class);
       } else
           throw new ServiceException("La fecha ingresada es anterior a la fecha actual, " +
                   "intente con una fecha posterior.");
       return turnoDto;
   }

    @Override
    public List<TurnoDto> verTodosLosTurnos() {
        List<TurnoDto> turnos = new ArrayList<>();

        for(Turno turno : this.repository.findAll()) {
            turnos.add(mapper.getModelMapper().map(turno, TurnoDto.class));
        }

        return turnos;
    }

    @Override
    public TurnoDto verTurnoPorId(int id) {

        Optional<Turno> turno = this.repository.findById(id);

        TurnoDto turnoDto = null;

        if (turno.isPresent()) {
            turnoDto =  mapper.getModelMapper().map(turno.get(), TurnoDto.class);

        }
        return  turnoDto;

    }

    @Override
    public List<TurnoDto> verTurnosPorFecha(LocalDate fecha) {
        return null;
    }


    // PRIVATE METHODS

    private Turno guardarTurno (Turno turno) {
        TurnoDto turnoDto = mapper.getModelMapper().map(turno, TurnoDto.class);
       repository.save(turno);
        return turno;
    }

}
