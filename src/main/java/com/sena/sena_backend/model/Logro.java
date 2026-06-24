package com.sena.sena_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "logros")
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 700)
    private String descripcion;

    private String icono;

    private String tipo;

    private Integer meta;

    private Boolean activo = true;

    public Logro() {
    }

    public Logro(String nombre, String descripcion, String icono, String tipo, Integer meta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icono = icono;
        this.tipo = tipo;
        this.meta = meta;
        this.activo = true;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getIcono() { return icono; }
    public String getTipo() { return tipo; }
    public Integer getMeta() { return meta; }
    public Boolean getActivo() { return activo; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setIcono(String icono) { this.icono = icono; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setMeta(Integer meta) { this.meta = meta; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}