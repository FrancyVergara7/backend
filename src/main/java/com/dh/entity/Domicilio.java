package com.dh.entity;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
public class Domicilio {

    @Id
    @SequenceGenerator(name = "domicilio_secuencia", sequenceName = "domicilio_secuencia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_secuencia")
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    public Domicilio() {
    }

    public Domicilio(Long id, String calle, int numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
