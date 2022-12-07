package com.example.clinica.controller;

import com.example.clinica.model.dto.AgendarTurnoDto;
import com.example.clinica.model.dto.OdontologoDto;
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
    public ResponseEntity<TurnoDto> crearTurno(@RequestBody AgendarTurnoDto agendarTurnoDto) throws Exception {
        ResponseEntity<TurnoDto> respuesta = null;
        try {
            respuesta = ResponseEntity.ok(turnoService.crearTurno(agendarTurnoDto));
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
        List<TurnoDto> resultado = turnoService.verTodosLosTurnos();
        return ResponseEntity.ok(resultado);

    }
   @GetMapping("obtenerFecha/{fecha}")
   public ResponseEntity<List<TurnoDto>> listarTurnosPorFecha(LocalDate fecha){
        return ResponseEntity.ok(turnoService.verTurnosPorFecha(fecha));
   }


}
