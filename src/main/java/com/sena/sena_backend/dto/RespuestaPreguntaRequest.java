package com.sena.sena_backend.dto;

public record RespuestaPreguntaRequest(
        Long preguntaId,
        Long opcionId
) {
}