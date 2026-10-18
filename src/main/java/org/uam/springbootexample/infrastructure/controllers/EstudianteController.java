package org.uam.springbootexample.infrastructure.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.uam.springbootexample.application.services.EstudianteService;
import org.uam.springbootexample.domain.models.Estudiante;
import org.uam.springbootexample.infrastructure.dto.EstudianteRequest;
import org.uam.springbootexample.infrastructure.dto.EstudianteResponse;
import org.uam.springbootexample.infrastructure.dto.PageResponse;
import org.uam.springbootexample.infrastructure.persistence.mappers.EstudianteMapper;

@RestController @RequestMapping("/api/estudiante") public class EstudianteController {
    private final EstudianteMapper mapper;
    private final EstudianteService service;

    public EstudianteController(EstudianteMapper mapper, EstudianteService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping public ResponseEntity<PageResponse<EstudianteResponse>> getAll(
        @RequestParam(defaultValue = "0")
        @Min(value = 0, message = "La página no puede ser negativa.") int page,

        @RequestParam(defaultValue = "10")
        @Min(value = 1, message = "El tamaño de página debe ser al menos 1.")
        @Max(value = 50, message = "El tamaño de página no puede ser mayor a 50.")
        int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        return ResponseEntity.ok(PageResponse.from(
            service.getAllEstudiantes(pageable),
            mapper::toResponse
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> getById(@PathVariable Long id) {
        return service.getEstudiante(id)
            .map(estudiante -> ResponseEntity.ok(mapper.toResponse(estudiante)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public ResponseEntity<EstudianteResponse> post(
        @Valid @RequestBody EstudianteRequest request
    ) {
        Estudiante creado = service.post(mapper.fromRequest(null, request));

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapper.toResponse(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> put(
        @PathVariable Long id,
        @Valid @RequestBody EstudianteRequest request
    ) {
        Estudiante actualizado = service.put(mapper.fromRequest(id, request));

        return ResponseEntity.ok(mapper.toResponse(actualizado));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstudianteResponse> patch(
        @PathVariable Long id,
        @RequestBody EstudianteRequest request
    ) {
        Estudiante actualizado = service.patch(id, mapper.fromRequest(id, request));

        return ResponseEntity.ok(mapper.toResponse(actualizado));
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
