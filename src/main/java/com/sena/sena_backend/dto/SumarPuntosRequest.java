package com.sena.sena_backend.dto;

public record SumarPuntosRequest(
        Integer puntos,
        String tipo,
        String titulo,
        String descripcion
) {
}