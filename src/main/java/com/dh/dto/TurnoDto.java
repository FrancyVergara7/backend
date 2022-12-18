package com.dh.dto;

import com.dh.entity.Odontologo;
import com.dh.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TurnoDto {

    private Long id;
    @NotNull
    public LocalDate fecha;
    @NotNull
    public LocalTime hora;
    @NotNull
    public PacienteDto paciente;
    @NotNull
    public OdontologoDto odontologo;

    public Long getId() {
        return id;
    }

    public TurnoDto() {
    }

    public TurnoDto(Long id, LocalDate fecha, LocalTime hora, PacienteDto paciente, OdontologoDto odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }
}