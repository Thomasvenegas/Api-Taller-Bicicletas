package com.example.api_taller_bicicleta.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

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


        //Fk Bicileta
        @ManyToOne
        @JoinColumn(name = "bicicleta_id", nullable = false)
        private Bicicleta bicicleta;

        //Fk Usuario
        @ManyToOne
        @JoinColumn(name = "usuario_id", nullable = false)
        private Usuario usuario;




        @OneToMany(mappedBy = "ordenTrabajo")
        @JsonManagedReference
        private List<OrdenServicio> ordenServicios;




    }


