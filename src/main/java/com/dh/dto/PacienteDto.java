package com.dh.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PacienteDto {

    private Long id;
    @NotNull
    public String nombre;
    @NotNull
    public String apellido;
    public int dni;
    public LocalDate fechaAlta;
    public DomicilioDto domicilio;

    public Long getId() {
        return id;
    }

    public PacienteDto() {
    }

    public PacienteDto(Long id, String nombre, String apellido, int dni, LocalDate fechaAlta, DomicilioDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public DomicilioDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDto domicilio) {
        this.domicilio = domicilio;
    }
}
