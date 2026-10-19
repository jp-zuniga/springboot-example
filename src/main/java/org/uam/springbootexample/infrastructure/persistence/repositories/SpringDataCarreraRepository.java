package org.uam.springbootexample.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.uam.springbootexample.infrastructure.persistence.entities.CarreraEntity;

public interface SpringDataCarreraRepository
    extends JpaRepository<CarreraEntity, Long>
{
}
