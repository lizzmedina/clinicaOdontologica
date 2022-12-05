package com.example.clinica.controller;

import com.example.clinica.model.dto.TurnoDto;
import com.example.clinica.service.ITurnoService;
import com.example.clinica.service.implement.TurnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    public TurnoServiceImpl turnoService;

    @PostMapping(value = "/agregar")
    public ResponseEntity<TurnoDto> crearTurno(@RequestBody int idPaciente, int idOdontologo, LocalDate fecha) throws Exception {
        ResponseEntity<TurnoDto> respuesta = null;
        try {
            respuesta = ResponseEntity.ok(turnoService.crearTurno(idPaciente, idOdontologo,fecha));
        }catch (Exception e){
            throw new Exception(e);
        }
        return respuesta;
    }

    @GetMapping("/obtener/{id}")
    public TurnoDto obtenerTurnoPorId(@PathVariable int id){
        return turnoService.verTurnoPorId(id);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<TurnoDto>> listarTodosLosTurnos(){

        return ResponseEntity.ok(turnoService.verTodosLosTurnos());

    }
   @GetMapping("obtener/{porFecha}")
   public ResponseEntity<List<TurnoDto>> listarTurnosPorFecha(LocalDate fecha){
        return ResponseEntity.ok(turnoService.verTurnosPorFecha(fecha));
   }


}
