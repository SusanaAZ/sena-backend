package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.PremiumUsuario;

public class PremiumResponse {

    private boolean premium;
    private String mensaje;
    private Boolean activo;
    private String tipoPlan;

    public PremiumResponse(boolean premium, String mensaje) {
        this.premium = premium;
        this.mensaje = mensaje;
        this.activo = premium;
        this.tipoPlan = premium ? "PREMIUM" : "GRATIS";
    }

    public PremiumResponse(Boolean activo, String tipoPlan) {
        this.activo = activo;
        this.tipoPlan = tipoPlan;
        this.premium = Boolean.TRUE.equals(activo);
        this.mensaje = Boolean.TRUE.equals(activo) ? "Cuenta Premium" : "Cuenta Gratis";
    }

    public static PremiumResponse from(PremiumUsuario premium) {
        return new PremiumResponse(
                premium.getActivo(),
                premium.getTipoPlan()
        );
    }

    public boolean isPremium() {
        return premium;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Boolean getActivo() {
        return activo;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }
}