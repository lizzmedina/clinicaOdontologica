package com.example.clinica.service.implement;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.dto.PacienteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    void crear() throws ResourceNotFoundException {
        PacienteDto pacienteDto1 = new PacienteDto();

        pacienteDto1.setNombre("francisco");
        pacienteDto1.setApellido("ramirez");
        pacienteDto1.setDctoIdentidad("abc006");
        pacienteDto1.setFechaIngreso(LocalDate.parse("2022-02-03"));

        pacienteService.crear(pacienteDto1);

        PacienteDto pacienteDtoFrancisco = pacienteService.obtenerPorId(1);

        assertNotNull(pacienteDtoFrancisco);

    }

}