package com.qds.prueba.notas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @Column(name = "nombre_curso")
    private String nombreCurso;

    @Column(name = "nota_curso")
    private Double notaCurso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(estudiante, nota.estudiante) && Objects.equals(nombreCurso, nota.nombreCurso) && Objects.equals(notaCurso, nota.notaCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estudiante, nombreCurso, notaCurso);
    }
}
