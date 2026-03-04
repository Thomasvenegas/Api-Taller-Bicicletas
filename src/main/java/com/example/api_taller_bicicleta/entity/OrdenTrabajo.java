package com.example.api_taller_bicicleta.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "orden_trabajo")

public class OrdenTrabajo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Date fechaIngreso;
        private Date fechaEstimada;
        private String estado;
        private String observaciones;


        //llave foranea Bicileta
        @ManyToOne
        @JoinColumn(name = "bicicleta_id", nullable = false)
        private Bicicleta bicicleta;

        //llave foranea Usuario
        @ManyToOne
        @JoinColumn(name = "usuario_id", nullable = false)
        private Usuario usuario;
    }


