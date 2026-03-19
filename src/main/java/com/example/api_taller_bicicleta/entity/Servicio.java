package com.example.api_taller_bicicleta.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Entity
@Table(name = "servicio")
public class Servicio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String descripcion;
    private Long precio;

    @ManyToMany(mappedBy = "servicios")
    @JsonBackReference
    private Set<OrdenServicio> ordenServicios;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecio() {
        return precio;
    }

    public Long setPrecio(Long precio) {
        this.precio = precio;
        return precio;
    }

    public Set<OrdenServicio> getOrdenServicios() {
        return ordenServicios;
    }

    public void setOrdenServicios(Set<OrdenServicio> ordenServicios) {
        this.ordenServicios = ordenServicios;
    }
}
