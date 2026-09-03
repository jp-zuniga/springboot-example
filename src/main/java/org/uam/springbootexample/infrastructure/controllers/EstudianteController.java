package org.uam.springbootexample.infrastructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.uam.springbootexample.application.services.EstudianteService;
import org.uam.springbootexample.domain.models.Estudiante;
import org.uam.springbootexample.infrastructure.dto.EstudianteRequest;
import org.uam.springbootexample.infrastructure.dto.EstudianteResponse;
import org.uam.springbootexample.infrastructure.persistence.mappers.EstudianteMapper;

@RestController @RequestMapping("/api/estudiante") public class EstudianteController {
    private final EstudianteMapper mapper;
    private final EstudianteService service;

    public EstudianteController(EstudianteMapper mapper, EstudianteService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping public ResponseEntity<List<EstudianteResponse>> getAll() {
        List<EstudianteResponse> estudiantes =
            service.getAllEstudiantes().stream().map(mapper::toResponse).toList();

        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> getById(@PathVariable Long id) {
        return service.getEstudiante(id)
            .map(estudiante -> ResponseEntity.ok(mapper.toResponse(estudiante)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public ResponseEntity<EstudianteResponse> post(
        @RequestBody EstudianteRequest request
    ) {
        Estudiante estudiante = mapper.fromRequest(null, request);

        estudiante.puedeRegistrar();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapper.toResponse(service.post(estudiante)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> put(
        @PathVariable Long id,
        @RequestBody EstudianteRequest request
    ) {
        Estudiante estudiante = mapper.fromRequest(id, request);

        estudiante.puedeRegistrar();

        return ResponseEntity.ok(mapper.toResponse(service.put(estudiante)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstudianteResponse> patch(
        @PathVariable Long id,
        @RequestBody EstudianteRequest request
    ) {
        Estudiante cambios =
            new Estudiante(id, request.nombre(), request.correo(), request.edad());

        Estudiante actualizado = service.patch(id, cambios);

        return ResponseEntity.ok(mapper.toResponse(actualizado));
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}