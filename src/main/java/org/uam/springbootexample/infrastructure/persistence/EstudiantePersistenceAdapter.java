package org.uam.springbootexample.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import org.uam.springbootexample.application.repositories.EstudianteRepository;
import org.uam.springbootexample.domain.models.Estudiante;
import org.uam.springbootexample.infrastructure.persistence.mappers.EstudianteMapper;
import org.uam.springbootexample.infrastructure.persistence.repositories.SpringDataEstudianteRepository;

@Component public class EstudiantePersistenceAdapter implements EstudianteRepository {
    private final SpringDataEstudianteRepository repository;
    private final EstudianteMapper mapper;

    public EstudiantePersistenceAdapter(
        SpringDataEstudianteRepository repository,
        EstudianteMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override public Estudiante create(Estudiante estudiante) {
        return mapper.toDomain(repository.save(mapper.toEntity(estudiante)));
    }

    @Override public Estudiante update(Estudiante estudiante) {
        return mapper.toDomain(repository.save(mapper.toEntity(estudiante)));
    }

    @Override public Optional<Estudiante> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override public Page<Estudiante> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }

    @Override public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
