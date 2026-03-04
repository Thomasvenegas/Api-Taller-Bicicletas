package com.example.api_taller_bicicleta.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "servicio")
@Data
public class Servicio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String descripcion;
    private Long precio;

    @OneToMany(mappedBy = "servicio")
    private List<OrdenServicio> ordenServicios;

}
