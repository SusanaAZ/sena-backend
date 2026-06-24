package com.sena.sena_backend.dto;

public record RankingResponse(
        Long usuarioId,
        String nombre,
        String username,
        Integer avatar,
        Integer puntos,
        Integer nivel
) {
}