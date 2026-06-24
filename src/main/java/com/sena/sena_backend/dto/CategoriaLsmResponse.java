package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.CategoriaLsm;

public record CategoriaLsmResponse(
        Long id,
        String nombre,
        String descripcion,
        String icono
) {
    public static CategoriaLsmResponse from(CategoriaLsm categoria) {
        return new CategoriaLsmResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getIcono()
        );
    }
}