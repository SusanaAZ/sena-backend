package com.sena.sena_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "favoritos_palabra",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario_id", "palabra_id"})
        }
)
public class FavoritoPalabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "palabra_id", nullable = false)
    private PalabraLsm palabra;

    private LocalDateTime fechaGuardado = LocalDateTime.now();

    public FavoritoPalabra() {
    }

    public FavoritoPalabra(Usuario usuario, PalabraLsm palabra) {
        this.usuario = usuario;
        this.palabra = palabra;
        this.fechaGuardado = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public PalabraLsm getPalabra() { return palabra; }
    public LocalDateTime getFechaGuardado() { return fechaGuardado; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setPalabra(PalabraLsm palabra) { this.palabra = palabra; }
    public void setFechaGuardado(LocalDateTime fechaGuardado) { this.fechaGuardado = fechaGuardado; }
}