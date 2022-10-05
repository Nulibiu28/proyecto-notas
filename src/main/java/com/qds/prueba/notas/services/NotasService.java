package com.qds.prueba.notas.services;

import com.qds.prueba.notas.entity.Nota;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface NotasService {
    Map<String,Object> listarNotas(Long idEstudiante);
    Map<String,Object> guardarNotas(Long idEstudiante, List<Nota> notas);
}
