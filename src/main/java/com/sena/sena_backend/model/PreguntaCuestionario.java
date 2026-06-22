package com.sena.sena_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntas_cuestionario")
public class PreguntaCuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuestionario_id", nullable = false)
    private Cuestionario cuestionario;

    @Column(nullable = false, length = 800)
    private String pregunta;

    private String imagenUrl;

    private Boolean activo = true;

    public PreguntaCuestionario() {
    }

    public Long getId() { return id; }
    public Cuestionario getCuestionario() { return cuestionario; }
    public String getPregunta() { return pregunta; }
    public String getImagenUrl() { return imagenUrl; }
    public Boolean getActivo() { return activo; }

    public void setId(Long id) { this.id = id; }
    public void setCuestionario(Cuestionario cuestionario) { this.cuestionario = cuestionario; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}