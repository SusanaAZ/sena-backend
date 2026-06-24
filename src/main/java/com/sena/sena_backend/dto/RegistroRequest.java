package com.sena.sena_backend.dto;

public record RegistroRequest(
        String nombre,
        String username,
        String correo,
        String password
) {
}