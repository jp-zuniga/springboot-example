package org.uam.springbootexample.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.uam.springbootexample.domain.exceptions.EstudianteDebeSerAdulto;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EstudianteDebeSerAdulto.class)
    public ResponseEntity<String> handleEstudianteDebeSerAdulto(EstudianteDebeSerAdulto ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}