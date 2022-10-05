package com.qds.prueba.notas.daos;

import com.qds.prueba.notas.entity.Estudiante;
import com.qds.prueba.notas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotasRepository extends JpaRepository<Nota, Long> {
    Optional<List<Nota>> findByEstudiante(Estudiante estudiante);
}
