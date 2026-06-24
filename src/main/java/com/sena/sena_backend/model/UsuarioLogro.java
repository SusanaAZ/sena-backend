package com.sena.sena_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "usuario_logros",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario_id", "logro_id"})
        }
)
public class UsuarioLogro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "logro_id", nullable = false)
    private Logro logro;

    private LocalDateTime fechaDesbloqueo = LocalDateTime.now();

    public UsuarioLogro() {
    }

    public UsuarioLogro(Usuario usuario, Logro logro) {
        this.usuario = usuario;
        this.logro = logro;
        this.fechaDesbloqueo = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Logro getLogro() { return logro; }
    public LocalDateTime getFechaDesbloqueo() { return fechaDesbloqueo; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setLogro(Logro logro) { this.logro = logro; }
    public void setFechaDesbloqueo(LocalDateTime fechaDesbloqueo) { this.fechaDesbloqueo = fechaDesbloqueo; }
}