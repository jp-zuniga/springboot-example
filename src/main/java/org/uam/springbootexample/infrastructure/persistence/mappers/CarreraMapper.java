package org.uam.springbootexample.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import org.uam.springbootexample.domain.models.Carrera;
import org.uam.springbootexample.infrastructure.dto.CarreraRequest;
import org.uam.springbootexample.infrastructure.dto.CarreraResponse;
import org.uam.springbootexample.infrastructure.persistence.entities.CarreraEntity;

@Component public class CarreraMapper {
    public Carrera fromRequest(Long id, CarreraRequest request) {
        if (request == null) {
            return null;
        }

        return new Carrera(id, request.nombre(), request.descripcion());
    }

    public CarreraEntity toEntity(Carrera carrera) {
        if (carrera == null) {
            return null;
        }

        return CarreraEntity.builder()
            .id(carrera.id())
            .nombre(carrera.nombre())
            .descripcion(carrera.descripcion())
            .build();
    }

    public CarreraResponse toResponse(Carrera carrera) {
        if (carrera == null) {
            return null;
        }

        return new CarreraResponse(
            carrera.id(),
            carrera.nombre(),
            carrera.descripcion()
        );
    }

    public Carrera toDomain(CarreraEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Carrera(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }
}
