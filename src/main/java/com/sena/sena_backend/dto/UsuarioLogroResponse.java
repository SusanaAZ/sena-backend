package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.UsuarioLogro;
import java.time.LocalDateTime;

public record UsuarioLogroResponse(
        Long id,
        LogroResponse logro,
        LocalDateTime fechaDesbloqueo
) {
    public static UsuarioLogroResponse from(UsuarioLogro usuarioLogro) {
        return new UsuarioLogroResponse(
                usuarioLogro.getId(),
                LogroResponse.from(usuarioLogro.getLogro()),
                usuarioLogro.getFechaDesbloqueo()
        );
    }
}