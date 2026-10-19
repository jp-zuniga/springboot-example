package org.uam.springbootexample.application.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.uam.springbootexample.domain.models.Carrera;

public interface CarreraRepository {
    Carrera create(Carrera carrera);

    Carrera update(Carrera carrera);

    Optional<Carrera> findById(Long id);

    boolean existsById(Long id);

    Page<Carrera> findAll(Pageable pageable);

    void deleteById(Long id);
}
