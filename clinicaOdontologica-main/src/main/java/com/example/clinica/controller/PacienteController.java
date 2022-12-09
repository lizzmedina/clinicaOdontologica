package com.example.clinica.controller;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.service.implement.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<PacienteDto> crearPaciente(@RequestBody PacienteDto dto) throws ResourceNotFoundException {
        ResponseEntity<PacienteDto> respuesta;
        respuesta = ResponseEntity.ok(service.crear(dto));

        return respuesta;
    }


    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> listarTodos(){
        List<PacienteDto> resultado = service.encontrarTodos();

        return ResponseEntity.ok(resultado);
    }

    @RequestMapping(value = "/porId/{id}", method = RequestMethod.GET)
    public PacienteDto obtenerPacientePorId(@PathVariable int id) throws ResourceNotFoundException {
        return service.obtenerPorId(id);
    }

  @RequestMapping(value = "/nombre/{nombre}", method = RequestMethod.GET)
    public  List<PacienteDto> obtenerPacientePorNombre(@PathVariable String nombre){
        return service.obtenerPorNombre(nombre);
    }
    @RequestMapping(value = "/apellido/{apellido}", method = RequestMethod.GET)
    public  List<PacienteDto> listarPacientesPorApellido(@PathVariable String apellido){
        return service.obtenerPorApellido(apellido);
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<?> borrarPaciente(@PathVariable int id) throws ResourceNotFoundException {
       service.borrar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDto dto) throws ResourceNotFoundException {
        service.actualizar(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
