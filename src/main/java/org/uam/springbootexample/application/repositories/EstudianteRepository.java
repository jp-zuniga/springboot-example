package org.uam.springbootexample.application.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.uam.springbootexample.domain.models.Estudiante;

public interface EstudianteRepository {
    Estudiante create(Estudiante estudiante);

    Estudiante update(Estudiante estudiante);

    Optional<Estudiante> findById(Long id);

    Page<Estudiante> findAll(Pageable pageable);

    void deleteById(Long id);
}
