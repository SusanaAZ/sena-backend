package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.PremiumUsuario;

public record PremiumResponse(
        Boolean activo,
        String tipoPlan
) {
    public static PremiumResponse from(PremiumUsuario premium) {
        return new PremiumResponse(
                premium.getActivo(),
                premium.getTipoPlan()
        );
    }
}