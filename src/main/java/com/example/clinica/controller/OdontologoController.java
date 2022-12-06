package com.example.clinica.controller;

import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.model.dto.PacienteDto;
import com.example.clinica.service.implement.OdontologoService;
import com.example.clinica.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

   @Autowired
   private OdontologoService  odontologoService;



    @PostMapping
    public ResponseEntity<OdontologoDto> crearOdontologo(@RequestBody OdontologoDto odont)  {
        ResponseEntity<OdontologoDto> respuesta = null;
        respuesta = ResponseEntity.ok(odontologoService.crear(odont));

        return respuesta;
    }
    /*@PostMapping("/crearTorre")
    public ResponseEntity<?> creaTorre(@RequestBody TorreDto torreDto){

        if(StringUtils.isBlank(torreDto.getNombreTorre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(torreDto.getCantidadAptos()<0 || (Integer) torreDto.getCantidadAptos() == null)
            return new ResponseEntity(new Mensaje("La cantidad de aptos debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        if(torreService.existsByNombreTorre(torreDto.getNombreTorre()))
            return new ResponseEntity(new Mensaje("Ya existe una torre con ese nombre"), HttpStatus.BAD_REQUEST);

        Torre torre = new Torre(torreDto.getNombreTorre(), torreDto.getCantidadAptos());
        torreService.saveTorre(torre);
        return new ResponseEntity(new Mensaje("Torre creada"), HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> listarTodosLosOdontologos(){

        return ResponseEntity.ok(odontologoService.encontrarTodos());

    }
    //diseño de apis

    @GetMapping("/obtenerx/{id}")
    public OdontologoDto obtenerOdontologoPorId(@PathVariable int id){
        return odontologoService.obtenerPorId(id);
    }

    @GetMapping("obtener/{matricula}")
    public List<OdontologoDto> obtenerPOdontologoPorMatricula(@PathVariable String matricula){
        return odontologoService.obtenerPorMatricula(matricula);
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<?> borrarOdontologo(@PathVariable int id){
        odontologoService.borrar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/actualizar")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDto dto) throws ServiceException {
        odontologoService.actualizar(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }




}
