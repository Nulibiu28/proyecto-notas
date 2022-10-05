package com.qds.prueba.notas.utils;

import com.qds.prueba.notas.entity.Estudiante;
import com.qds.prueba.notas.entity.Nota;

public class Data {

    public static final Estudiante ESTUDIANTE_01 = Estudiante.builder()
            .nombres("Italo")
            .apellidos("Lazo Solis")
            .build();

    public static final Estudiante ESTUDIANTE_02 = Estudiante.builder()
            .nombres("Luna")
            .apellidos("Linares Paredes")
            .build();
    public static final Nota NOTA_01 = Nota.builder()
            .id(1L)
            .nombreCurso("Aritmetica")
            .notaCurso(16.5)
            .estudiante(ESTUDIANTE_01)
            .build();

    public static final Nota NOTA_02 = Nota.builder()
            .id(2L)
            .nombreCurso("Geometria")
            .notaCurso(14.7)
            .estudiante(ESTUDIANTE_01)
            .build();

    public static final Nota NOTA_03 = Nota.builder()
            .id(3L)
            .nombreCurso("Trigonometria")
            .notaCurso(17.5)
            .estudiante(ESTUDIANTE_01)
            .build();

    public static final Nota NOTA_04 = Nota.builder()
            .id(4L)
            .nombreCurso("Fisica")
            .notaCurso(19.2)
            .estudiante(ESTUDIANTE_02)
            .build();

    public static final Nota NOTA_05 = Nota.builder()
            .id(5L)
            .nombreCurso("Quimica")
            .notaCurso(18.7)
            .estudiante(ESTUDIANTE_02)
            .build();
}
