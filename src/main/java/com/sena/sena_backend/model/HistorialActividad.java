package com.sena.sena_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_actividad")
public class HistorialActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String tipo;

    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private Integer puntosGanados = 0;

    private LocalDateTime fecha = LocalDateTime.now();

    public HistorialActividad() {
    }

    public HistorialActividad(Usuario usuario, String tipo, String titulo, String descripcion, Integer puntosGanados) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntosGanados = puntosGanados;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getPuntosGanados() {
        return puntosGanados;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPuntosGanados(Integer puntosGanados) {
        this.puntosGanados = puntosGanados;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}