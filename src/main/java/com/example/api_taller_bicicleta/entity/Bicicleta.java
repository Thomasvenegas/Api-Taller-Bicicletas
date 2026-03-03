package com.example.api_taller_bicicleta.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Bicicleta")
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String tipo;
    private Long numeroSerie;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)//usuario_id = foreign key
    @JsonBackReference
    private Usuario usuario;
}
