package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.FavoritoPalabra;
import java.time.LocalDateTime;

public record FavoritoResponse(
        Long id,
        PalabraLsmResponse palabra,
        LocalDateTime fechaGuardado
) {
    public static FavoritoResponse from(FavoritoPalabra favorito) {
        return new FavoritoResponse(
                favorito.getId(),
                PalabraLsmResponse.from(favorito.getPalabra()),
                favorito.getFechaGuardado()
        );
    }
}