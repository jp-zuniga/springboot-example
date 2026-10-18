package org.uam.springbootexample.infrastructure;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.uam.springbootexample.domain.exceptions.EstudianteDebeSerAdulto;

@ControllerAdvice public class GlobalExceptionHandler {
    @ExceptionHandler(EstudianteDebeSerAdulto.class)
    public ResponseEntity<String> handleEstudianteDebeSerAdulto(EstudianteDebeSerAdulto ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex
    ) {
        Map<String, String> errores = new LinkedHashMap<>();

        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error -> errores.putIfAbsent(
                error.getField(),
                error.getDefaultMessage()
            ));

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleHandlerMethodValidation(
        HandlerMethodValidationException ex
    ) {
        Map<String, String> errores = new LinkedHashMap<>();

        ex.getParameterValidationResults()
            .forEach(result -> result.getResolvableErrors()
                .forEach(error -> errores.putIfAbsent(
                    result.getMethodParameter()
                        .getParameterName(),
                    error.getDefaultMessage()
                )));

        return ResponseEntity.badRequest().body(errores);
    }
}
