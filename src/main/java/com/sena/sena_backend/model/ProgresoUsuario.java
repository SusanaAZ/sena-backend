package com.sena.sena_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "progreso_usuario")
public class ProgresoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    private Integer puntos = 0;

    private Integer nivel = 1;

    private Integer racha = 0;

    private Integer leccionesCompletadas = 0;

    private Integer leccionesHoy = 0;

    private LocalDate ultimaPractica;

    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    public ProgresoUsuario() {
    }

    public ProgresoUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.puntos = 0;
        this.nivel = 1;
        this.racha = 0;
        this.leccionesCompletadas = 0;
        this.leccionesHoy = 0;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public Integer getNivel() {
        return nivel;
    }

    public Integer getRacha() {
        return racha;
    }

    public Integer getLeccionesCompletadas() {
        return leccionesCompletadas;
    }

    public Integer getLeccionesHoy() {
        return leccionesHoy;
    }

    public LocalDate getUltimaPractica() {
        return ultimaPractica;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public void setRacha(Integer racha) {
        this.racha = racha;
    }

    public void setLeccionesCompletadas(Integer leccionesCompletadas) {
        this.leccionesCompletadas = leccionesCompletadas;
    }

    public void setLeccionesHoy(Integer leccionesHoy) {
        this.leccionesHoy = leccionesHoy;
    }

    public void setUltimaPractica(LocalDate ultimaPractica) {
        this.ultimaPractica = ultimaPractica;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}