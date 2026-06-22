package com.sena.sena_backend.dto;

import java.util.List;

public record ResolverCuestionarioRequest(
        Long cuestionarioId,
        List<RespuestaPreguntaRequest> respuestas
) {
}