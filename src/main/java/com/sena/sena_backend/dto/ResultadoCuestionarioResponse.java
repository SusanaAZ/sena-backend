package com.sena.sena_backend.dto;

public record ResultadoCuestionarioResponse(
        Integer totalPreguntas,
        Integer correctas,
        Integer incorrectas,
        Integer puntosGanados,
        String mensaje
) {
}