package com.example.clinica.persistence.repository;
import com.example.clinica.persistence.entities.Odontologo;
import com.example.clinica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
    @Query(value = "SELECT * FROM Turnos t where t.fecha = :fecha ", nativeQuery = true)
    List<Turno> buscarTurnosPorFecha(@Param("fecha")LocalDate fecha);

}
