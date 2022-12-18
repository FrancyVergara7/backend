package com.dh.dto;

import javax.validation.constraints.NotNull;

public class OdontologoDto {
    private Long id;
    @NotNull
    public String nombre;
    @NotNull
    public String apellido;

    public Integer numeroMatricula;

    public Long getId() {
        return id;
    }

    public OdontologoDto() {
    }

    public OdontologoDto(Long id, String nombre, String apellido, Integer numeroMatricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroMatricula = numeroMatricula;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(Integer numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
}
