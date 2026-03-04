package com.example.api_taller_bicicleta.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orden_servicio")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private Boolean aprobadoCliente;

    //fk
    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id", nullable = false)
    @JsonBackReference
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;
}
