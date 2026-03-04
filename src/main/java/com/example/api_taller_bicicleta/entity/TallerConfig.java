package com.example.api_taller_bicicleta.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "taller_config")
@Data
public class TallerConfig {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Long capacidadMaxima;

}
