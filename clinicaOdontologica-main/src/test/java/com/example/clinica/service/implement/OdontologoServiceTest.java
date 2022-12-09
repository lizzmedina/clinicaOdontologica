package com.example.clinica.service.implement;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.OdontologoDto;
import com.example.clinica.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private IOdontologoService<OdontologoDto> odontologoService;

    @Test
    public void testCrearOdontologo() throws ResourceNotFoundException {
        OdontologoDto odontologoDto1 = new OdontologoDto();
        odontologoDto1.setNombre("francisco");
        odontologoDto1.setApellido("ramirez");
        odontologoDto1.setMatricula("abc006");
        OdontologoDto odontologoDto2 = new OdontologoDto();
        odontologoDto2.setNombre("francy");
        odontologoDto2.setApellido("Ramirez");
        odontologoDto2.setMatricula("ABC007");

        odontologoService.crear(odontologoDto1);
        odontologoService.crear(odontologoDto2);

        OdontologoDto odontologoLiza = odontologoService.obtenerPorId(1);
        OdontologoDto odontologoOtro = odontologoService.obtenerPorId(2);

        assertNotNull(odontologoLiza);
    }
    @Test
    public void testListarTodos(){
        List<OdontologoDto>  odontologos= odontologoService.encontrarTodos();

        assertNotNull(odontologos);
    }
}