package org.uam.springbootexample.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CarreraRequest(
    @NotBlank(message = "El nombre es obligatorio.") @Size(
        min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres."
    ) String nombre,

    @Size(
        max = 255, message = "La descripción no puede superar 255 caracteres."
    ) String descripcion
)
{
}
