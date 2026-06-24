package com.sena.sena_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "premium_usuario")
public class PremiumUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    private Boolean activo = false;

    private String tipoPlan = "GRATIS";

    private LocalDateTime fechaActivacion;

    public PremiumUsuario() {
    }

    public PremiumUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.activo = false;
        this.tipoPlan = "GRATIS";
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public LocalDateTime getFechaActivacion() {
        return fechaActivacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public void setFechaActivacion(LocalDateTime fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
}