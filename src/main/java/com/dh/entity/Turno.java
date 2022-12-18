package com.dh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate fecha;
    @Column(name = "time")
    private LocalTime hora;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Paciente paciente;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "odontologo_id")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Odontologo odontologo;

    public Turno() {
    }

    public Turno(LocalDate fecha, LocalTime hora, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha= fecha;
    }
    public void setHora(LocalTime hora) {
        this.hora= hora;
    }
}
