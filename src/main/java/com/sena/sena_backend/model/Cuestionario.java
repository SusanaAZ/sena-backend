package com.sena.sena_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cuestionarios")
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 700)
    private String descripcion;

    private String nivel;

    private Boolean activo = true;

    public Cuestionario() {
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getNivel() { return nivel; }
    public Boolean getActivo() { return activo; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}