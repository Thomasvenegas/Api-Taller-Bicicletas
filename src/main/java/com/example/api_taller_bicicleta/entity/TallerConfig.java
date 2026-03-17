package com.example.api_taller_bicicleta.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "taller_config")
@Data
public class TallerConfig {
    @Id
    private Long id;
    private Long capacidadMaxima;
    private Long bicicletasIngresadas;
    private Boolean tallerLleno;


    public Long getId() {
        return id;
    }

    public Boolean getTallerLleno() {
        return tallerLleno;
    }

    public void setTallerLleno(Boolean tallerLleno) {
        this.tallerLleno = tallerLleno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Long capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Long getBicicletasIngresadas() {
        return bicicletasIngresadas;
    }

    public void setBicicletasIngresadas(Long bicicletasIngresadas) {
        this.bicicletasIngresadas = bicicletasIngresadas;
    }
}

