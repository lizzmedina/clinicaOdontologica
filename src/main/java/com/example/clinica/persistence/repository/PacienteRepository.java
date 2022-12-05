package com.example.clinica.persistence.repository;

import com.example.clinica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query(value = "SELECT * FROM Paciente p where p.nombre = : valor || p.apellido = :valor  ", nativeQuery = true)
    Optional<Paciente> buscarPaciente(@Param("valor") String valor);

  /*  @Query("SELECT o FROM Paciente")
    Optional<List<Paciente>> buscarTodosLosPacientes();
  */
}
