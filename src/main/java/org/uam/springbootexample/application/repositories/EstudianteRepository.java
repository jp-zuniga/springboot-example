package org.uam.springbootexample.application.repositories;

import java.util.List;
import java.util.Optional;

import org.uam.springbootexample.domain.models.Estudiante;

public interface EstudianteRepository {
    Estudiante create(Estudiante estudiante);

    Estudiante update(Estudiante estudiante);

    Optional<Estudiante> findById(Long id);

    List<Estudiante> findAll();

    void deleteById(Long id);
}
