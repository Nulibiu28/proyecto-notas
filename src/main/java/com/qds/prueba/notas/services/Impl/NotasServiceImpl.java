package com.qds.prueba.notas.services.Impl;

import com.qds.prueba.notas.daos.EstudiantesRepository;
import com.qds.prueba.notas.daos.NotasRepository;
import com.qds.prueba.notas.entity.Nota;
import com.qds.prueba.notas.services.NotasService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotasServiceImpl implements NotasService {

    @Autowired
    private NotasRepository notasRepository;

    @Autowired
    private EstudiantesRepository estudiantesRepository;

    public NotasServiceImpl(NotasRepository notasRepository, EstudiantesRepository estudiantesRepository){
        this.notasRepository = notasRepository;
        this.estudiantesRepository = estudiantesRepository;
    }

    @Override
    public Map<String, Object> listarNotas(Long id) {
        Map<String, Object> map = new HashMap<>();
        estudiantesRepository.findById(id).ifPresentOrElse( estudiante -> {
            notasRepository.findByEstudiante(estudiante).ifPresentOrElse( notas -> {
                map.put("response", notas);
            }, () -> {
                map.put("response","No existen notas para este estudiantes");
            });
        },() -> {
            map.put("response","No existe un estudiante con ese ID");
        });

        return map;
    }

    @Override
    public Map<String, Object> guardarNotas(Long idEstudiante, List<Nota> notas) {
        Map<String, Object> map = new HashMap<>();
        if(notas.isEmpty()){
            map.put("response","Debe ingresar notas para el estudiante");
            return map;
        }
        estudiantesRepository.findById(idEstudiante).ifPresentOrElse( estudiante -> {
            notas.stream().map( n -> {
                n.setEstudiante(estudiante);
                return n;
            }).forEach( n -> {
                notasRepository.save(n);
            });
            map.put("response","Se agregaron las notas al estudiante "+estudiante.getNombres());
        },() -> {
            map.put("response","No existe un estudiante con ese ID");
        });

        return map;
    }
}
