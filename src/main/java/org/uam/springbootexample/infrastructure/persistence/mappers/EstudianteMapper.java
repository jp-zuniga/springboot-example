package org.uam.springbootexample.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import org.uam.springbootexample.domain.models.Estudiante;
import org.uam.springbootexample.infrastructure.dto.EstudianteRequest;
import org.uam.springbootexample.infrastructure.dto.EstudianteResponse;
import org.uam.springbootexample.infrastructure.persistence.entities.EstudianteEntity;

@Component public class EstudianteMapper {
    public Estudiante fromRequest(Long id, EstudianteRequest request) {
        if (request == null) {
            return null;
        }

        return new Estudiante(id, request.nombre(), request.correo(), request.edad());
    }

    public EstudianteEntity toEntity(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }

        return EstudianteEntity.builder()
            .id(estudiante.id())
            .nombre(estudiante.nombre())
            .correo(estudiante.correo())
            .edad(estudiante.edad())
            .build();
    }

    public EstudianteResponse toResponse(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }
        
        return new EstudianteResponse(
            estudiante.id(),
            estudiante.nombre(),
            estudiante.correo(),
            estudiante.edad()
        );
    }

    public Estudiante toDomain(EstudianteEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Estudiante(
            entity.getId(),
            entity.getNombre(),
            entity.getCorreo(),
            entity.getEdad()
        );
    }
}
