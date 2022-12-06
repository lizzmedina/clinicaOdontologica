package com.example.clinica.persistence.repository;

import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query(value = "SELECT * FROM Pacientes p where p.nombre = :nombre ", nativeQuery = true)
    List<Paciente> buscarPacientePorNombre(@Param("nombre") String nombre);
    @Query(value = "SELECT * FROM Pacientes p where p.apellido = :apellido ", nativeQuery = true)
    List<Paciente> buscarPacientePorApellido(@Param("apellido") String apellido);


}
