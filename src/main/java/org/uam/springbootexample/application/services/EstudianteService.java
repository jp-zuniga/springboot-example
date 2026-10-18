package org.uam.springbootexample.application.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.uam.springbootexample.application.repositories.EstudianteRepository;
import org.uam.springbootexample.domain.models.Estudiante;

@Service public class EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Transactional public Estudiante post(Estudiante estudiante) {
        estudiante.puedeRegistrar();

        return repository.create(estudiante);
    }

    @Transactional public Estudiante put(Estudiante estudiante) {
        estudiante.puedeRegistrar();

        return repository.update(estudiante);
    }

    @Transactional public Estudiante patch(Long id, Estudiante cambios) {
        Estudiante existente =
            repository.findById(id).orElseThrow(() -> new IllegalArgumentException((
                "Estudiante(id=%d) no encontrado."
            ).formatted(id)));

        String nuevoNombre =
            cambios.nombre() != null ? cambios.nombre() : existente.nombre();

        String nuevoCorreo =
            cambios.correo() != null ? cambios.correo() : existente.correo();

        Integer nuevaEdad = cambios.edad() != null ? cambios.edad() : existente.edad();

        Estudiante estudianteActualizado =
            new Estudiante(id, nuevoNombre, nuevoCorreo, nuevaEdad);

        estudianteActualizado.puedeRegistrar();

        return repository.update(estudianteActualizado);
    }

    @Transactional(readOnly = true) public Optional<Estudiante> getEstudiante(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Estudiante> getAllEstudiantes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional public void delete(Long id) {
        repository.deleteById(id);
    }
}
