package com.example.clinica.controller;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.service.implement.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

   @Autowired
   private OdontologoService  odontologoService;



    @PostMapping
    public ResponseEntity<OdontologoDto> crearOdontologo(@RequestBody OdontologoDto odont) throws ResourceNotFoundException {
        ResponseEntity<OdontologoDto> respuesta = null;
        respuesta = ResponseEntity.ok(odontologoService.crear(odont));

        return respuesta;
    }


    @GetMapping
    public ResponseEntity<List<OdontologoDto>> listarTodosLosOdontologos(){

        return ResponseEntity.ok(odontologoService.encontrarTodos());

    }

    @GetMapping("/obtenerx/{id}")
    public OdontologoDto obtenerOdontologoPorId(@PathVariable int id) throws ResourceNotFoundException {
        return odontologoService.obtenerPorId(id);
    }


    @GetMapping("obtener/{matricula}")
    public OdontologoDto obtenerOdontologoPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        return odontologoService.obtenerPorMatricula(matricula);
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<?> borrarOdontologo(@PathVariable int id) throws ResourceNotFoundException {
        odontologoService.borrar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/actualizar")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDto dto) throws ResourceNotFoundException {
        odontologoService.actualizar(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }




}
