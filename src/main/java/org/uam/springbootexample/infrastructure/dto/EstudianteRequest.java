package org.uam.springbootexample.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EstudianteRequest(
    @NotBlank(message = "El nombre es obligatorio.") @Size(
        min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres."
    ) String nombre,

    @NotBlank(message = "El correo es obligatorio.") @Email(
        message = "El correo no tiene un formato válido."
    ) String correo,

    @NotNull(message = "La edad es obligatoria.") @Positive(
        message = "La edad debe ser un valor positivo."
    ) Integer edad
)
{
}
