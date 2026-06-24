package com.sena.sena_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "opciones_pregunta")
public class OpcionPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pregunta_id", nullable = false)
    private PreguntaCuestionario pregunta;

    @Column(nullable = false)
    private String texto;

    private Boolean correcta = false;

    public OpcionPregunta() {
    }

    public Long getId() { return id; }
    public PreguntaCuestionario getPregunta() { return pregunta; }
    public String getTexto() { return texto; }
    public Boolean getCorrecta() { return correcta; }

    public void setId(Long id) { this.id = id; }
    public void setPregunta(PreguntaCuestionario pregunta) { this.pregunta = pregunta; }
    public void setTexto(String texto) { this.texto = texto; }
    public void setCorrecta(Boolean correcta) { this.correcta = correcta; }
}