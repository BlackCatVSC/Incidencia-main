package com.fixnow.incidencia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Incidencia {
    @Id //Genera automaticamente el ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank// Es para no no quede en blanco el atributo
    private String titulo;

    @NotBlank
    private String descripcion;


    @NotBlank//el pattern obliga al usuario a escribir lo que esta dentro del patter en este caso (ABIERTA,EN_PROCESO,RESUELTA y CERRADA).
    @Pattern(regexp = "ABIERTA|EN_PROCESO|RESUELTA|CERRADA", message = "Estado invalido, solo acepta: (ABIERTA,EN_PROCESO,RESUELTA y CERRADA).")
    private String estado;

    @NotBlank//el pattern obliga al usuario a escribir lo que esta dentro del patter en este caso (BAJA, MEDIA, ALTA Y CRITICA).
    @Pattern(regexp = "BAJA|MEDIA|ALTA|CRITICA", message = "Prioridad invalida, solo acepta: (BAJA, MEDIA, ALTA Y CRITICA).")
    private String prioridad;

    @NotBlank
    private String usuarioReportante;

    private String fechaRegistro;//No use aca el NotBlank por que se genera automaticamente el registro de la fecha en IncidenciaService.

    //Constructor vacio
    public Incidencia() {
    }

    //Constructores
    public Incidencia(Long id, String titulo, String descripcion, String estado, String prioridad, String usuarioReportante, String fechaRegistro) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioReportante = usuarioReportante;
        this.fechaRegistro = fechaRegistro;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getUsuarioReportante() {
        return usuarioReportante;
    }

    public void setUsuarioReportante(String usuarioReportante) {
        this.usuarioReportante = usuarioReportante;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
