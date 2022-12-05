package com.example.clinica.persistence.repository;
import com.example.clinica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {

}
