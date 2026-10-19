package org.uam.springbootexample.application.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.uam.springbootexample.application.exceptions.RecursoNoEncontrado;
import org.uam.springbootexample.application.repositories.CarreraRepository;
import org.uam.springbootexample.domain.models.Carrera;

@Service public class CarreraService {
    private final CarreraRepository repository;

    public CarreraService(CarreraRepository repository) {
        this.repository = repository;
    }

    @Transactional public Carrera post(Carrera carrera) {
        return repository.create(carrera);
    }

    @Transactional public Carrera put(Carrera carrera) {
        verificarExistencia(carrera.id());

        return repository.update(carrera);
    }

    @Transactional(readOnly = true) public Optional<Carrera> getCarrera(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Carrera> getAllCarreras(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional public void delete(Long id) {
        verificarExistencia(id);

        repository.deleteById(id);
    }

    private void verificarExistencia(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontrado("Carrera", id);
        }
    }
}
