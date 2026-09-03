package org.uam.springbootexample.domain.models;

import org.uam.springbootexample.domain.exceptions.EstudianteDebeSerAdulto;

public record Estudiante(Long id, String nombre, String correo, Integer edad) {
    public boolean esAdulto() {
        return edad != null && edad >= 18;
    }

    public void puedeRegistrar() {
        if (!esAdulto()) {
            throw new EstudianteDebeSerAdulto("%s debe ser un adulto.".formatted(nombre));
        }
    }
}
