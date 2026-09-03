package org.uam.springbootexample.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.uam.springbootexample.infrastructure.persistence.entities.EstudianteEntity;

public interface SpringDataEstudianteRepository
    extends JpaRepository<EstudianteEntity, Long>
{
}
