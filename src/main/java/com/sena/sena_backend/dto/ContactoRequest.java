package com.sena.sena_backend.dto;

public record ContactoRequest(
        String nombre,
        String correo,
        String mensaje
) {
}