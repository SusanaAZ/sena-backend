package com.sena.sena_backend.dto;

public record LoginRequest(
        String username,
        String password
) {
}