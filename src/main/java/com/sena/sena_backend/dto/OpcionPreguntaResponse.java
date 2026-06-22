package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.OpcionPregunta;

public record OpcionPreguntaResponse(
        Long id,
        String texto
) {
    public static OpcionPreguntaResponse from(OpcionPregunta opcion) {
        return new OpcionPreguntaResponse(opcion.getId(), opcion.getTexto());
    }
}