package com.qds.prueba.notas.daos;

import com.qds.prueba.notas.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudiantesRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByUsuario(String usuario);
}
