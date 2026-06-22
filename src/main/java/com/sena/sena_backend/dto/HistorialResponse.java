package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.HistorialActividad;
import java.time.LocalDateTime;

public record HistorialResponse(
        Long id,
        String tipo,
        String titulo,
        String descripcion,
        Integer puntosGanados,
        LocalDateTime fecha
) {
    public static HistorialResponse from(HistorialActividad actividad) {
        return new HistorialResponse(
                actividad.getId(),
                actividad.getTipo(),
                actividad.getTitulo(),
                actividad.getDescripcion(),
                actividad.getPuntosGanados(),
                actividad.getFecha()
        );
    }
}