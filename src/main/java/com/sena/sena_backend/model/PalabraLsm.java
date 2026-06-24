package com.sena.sena_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "palabras_lsm")
public class PalabraLsm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String palabra;

    @Column(length = 1000)
    private String descripcion;

    private String imagenUrl;

    private String nivel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaLsm categoria;

    private Boolean activa = true;

    public PalabraLsm() {
    }

    public PalabraLsm(String palabra, String descripcion, String imagenUrl, String nivel, CategoriaLsm categoria) {
        this.palabra = palabra;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.nivel = nivel;
        this.categoria = categoria;
        this.activa = true;
    }

    public Long getId() { return id; }
    public String getPalabra() { return palabra; }
    public String getDescripcion() { return descripcion; }
    public String getImagenUrl() { return imagenUrl; }
    public String getNivel() { return nivel; }
    public CategoriaLsm getCategoria() { return categoria; }
    public Boolean getActiva() { return activa; }

    public void setId(Long id) { this.id = id; }
    public void setPalabra(String palabra) { this.palabra = palabra; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setCategoria(CategoriaLsm categoria) { this.categoria = categoria; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}