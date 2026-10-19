package org.uam.springbootexample.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import org.uam.springbootexample.application.repositories.CarreraRepository;
import org.uam.springbootexample.domain.models.Carrera;
import org.uam.springbootexample.infrastructure.persistence.mappers.CarreraMapper;
import org.uam.springbootexample.infrastructure.persistence.repositories.SpringDataCarreraRepository;

@Component public class CarreraPersistenceAdapter implements CarreraRepository {
    private final SpringDataCarreraRepository repository;
    private final CarreraMapper mapper;

    public CarreraPersistenceAdapter(
        SpringDataCarreraRepository repository,
        CarreraMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override public Carrera create(Carrera carrera) {
        return mapper.toDomain(repository.save(mapper.toEntity(carrera)));
    }

    @Override public Carrera update(Carrera carrera) {
        return mapper.toDomain(repository.save(mapper.toEntity(carrera)));
    }

    @Override public Optional<Carrera> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override public Page<Carrera> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }

    @Override public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
