package com.example.clinica.persistence.repository;

import com.example.clinica.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    @Query(value = "SELECT * FROM Odontologos o where o.matricula = :matricula ", nativeQuery = true)
    Odontologo buscarOdontologoPorMatricula(@Param("matricula") String matricula);
}
