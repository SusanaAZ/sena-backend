package com.sena.sena_backend.dto;

import java.util.List;

public record CuestionarioResponse(
        Long id,
        String titulo,
        String descripcion,
        String nivel,
        List<PreguntaCuestionarioResponse> preguntas
) {
}