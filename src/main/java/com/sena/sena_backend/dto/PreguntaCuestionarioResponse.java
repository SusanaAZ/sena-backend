package com.sena.sena_backend.dto;

import java.util.List;

public record PreguntaCuestionarioResponse(
        Long id,
        String pregunta,
        String imagenUrl,
        List<OpcionPreguntaResponse> opciones
) {
}