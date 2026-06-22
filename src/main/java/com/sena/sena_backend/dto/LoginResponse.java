package com.sena.sena_backend.dto;

public record LoginResponse(
        String token,
        UsuarioResponse usuario
) {
}