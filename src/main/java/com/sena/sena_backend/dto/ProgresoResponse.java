package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.ProgresoUsuario;

public record ProgresoResponse(
        Integer puntos,
        Integer nivel,
        Integer racha,
        Integer leccionesCompletadas,
        Integer leccionesHoy
) {
    public static ProgresoResponse from(ProgresoUsuario progreso) {
        return new ProgresoResponse(
                progreso.getPuntos(),
                progreso.getNivel(),
                progreso.getRacha(),
                progreso.getLeccionesCompletadas(),
                progreso.getLeccionesHoy()
        );
    }
}