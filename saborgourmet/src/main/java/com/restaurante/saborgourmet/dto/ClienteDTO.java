package com.restaurante.saborgourmet.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    private String telefono;
    private String direccion;
    private Boolean activo;
}