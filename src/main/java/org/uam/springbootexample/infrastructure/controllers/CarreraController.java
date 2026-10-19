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

import org.uam.springbootexample.application.services.CarreraService;
import org.uam.springbootexample.domain.models.Carrera;
import org.uam.springbootexample.infrastructure.dto.CarreraRequest;
import org.uam.springbootexample.infrastructure.dto.CarreraResponse;
import org.uam.springbootexample.infrastructure.dto.PageResponse;
import org.uam.springbootexample.infrastructure.persistence.mappers.CarreraMapper;

@RestController @RequestMapping("/api/carrera") public class CarreraController {
    private final CarreraMapper mapper;
    private final CarreraService service;

    public CarreraController(CarreraMapper mapper, CarreraService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping public ResponseEntity<PageResponse<CarreraResponse>> getAll(
        @RequestParam(defaultValue = "0")
        @Min(value = 0, message = "La página no puede ser negativa.") int page,

        @RequestParam(defaultValue = "10")
        @Min(value = 1, message = "El tamaño de página debe ser al menos 1.")
        @Max(value = 50, message = "El tamaño de página no puede ser mayor a 50.")
        int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        return ResponseEntity.ok(PageResponse.from(
            service.getAllCarreras(pageable),
            mapper::toResponse
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraResponse> getById(@PathVariable Long id) {
        return service.getCarrera(id)
            .map(carrera -> ResponseEntity.ok(mapper.toResponse(carrera)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public ResponseEntity<CarreraResponse> post(
        @Valid @RequestBody CarreraRequest request
    ) {
        Carrera creada = service.post(mapper.fromRequest(null, request));

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapper.toResponse(creada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraResponse> put(
        @PathVariable Long id,
        @Valid @RequestBody CarreraRequest request
    ) {
        Carrera actualizada = service.put(mapper.fromRequest(id, request));

        return ResponseEntity.ok(mapper.toResponse(actualizada));
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
