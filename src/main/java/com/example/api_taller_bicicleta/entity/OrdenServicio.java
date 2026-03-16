package com.example.api_taller_bicicleta.entity;


import com.example.api_taller_bicicleta.enums.EstadoServicio;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "orden_servicio")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoServicio estado;


    //fk
    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id", nullable = false)
    @JsonBackReference
    private OrdenTrabajo ordenTrabajo;

    //fk
    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = true)
    private Servicio servicio;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoServicio getEstado() {
        return estado;
    }

    public void setEstado(EstadoServicio estado) {
        this.estado = estado;
    }

    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
