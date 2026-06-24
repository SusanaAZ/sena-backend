package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.PalabraLsm;

public record PalabraLsmResponse(
        Long id,
        String palabra,
        String descripcion,
        String imagenUrl,
        String nivel,
        CategoriaLsmResponse categoria
) {
    public static PalabraLsmResponse from(PalabraLsm palabra) {
        return new PalabraLsmResponse(
                palabra.getId(),
                palabra.getPalabra(),
                palabra.getDescripcion(),
                palabra.getImagenUrl(),
                palabra.getNivel(),
                CategoriaLsmResponse.from(palabra.getCategoria())
        );
    }
}