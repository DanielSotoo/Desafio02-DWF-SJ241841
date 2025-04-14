package com.restaurante.saborgourmet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 150)
    @Column(unique = true)
    private String email;

    @Size(max = 15)
    private String telefono;

    @Size(max = 255)
    private String direccion;

    @NotNull
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @NotNull
    private Boolean activo = true;
}