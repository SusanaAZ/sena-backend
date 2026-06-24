package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.Logro;

public record LogroResponse(
        Long id,
        String nombre,
        String descripcion,
        String icono,
        String tipo,
        Integer meta
) {
    public static LogroResponse from(Logro logro) {
        return new LogroResponse(
                logro.getId(),
                logro.getNombre(),
                logro.getDescripcion(),
                logro.getIcono(),
                logro.getTipo(),
                logro.getMeta()
        );
    }
}