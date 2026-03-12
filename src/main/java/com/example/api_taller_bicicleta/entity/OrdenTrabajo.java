package com.example.api_taller_bicicleta.entity;
import com.example.api_taller_bicicleta.enums.EstadoOrden;
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

        @Enumerated(EnumType.STRING)
        private EstadoOrden estado;
        private String observaciones;

        //Fk Bicileta
        @ManyToOne
        @JoinColumn(name = "bicicleta_id", nullable = false)
        private Bicicleta bicicleta;

        //Fk cliente
        @ManyToOne
        @JoinColumn(name = "cliente_id", nullable = false)
        private Usuario cliente;

        //Fk usuario mecanico
        @ManyToOne
        @JoinColumn(name = "mecanico_id", nullable = false)
        private Usuario mecanico;

        @OneToMany(mappedBy = "ordenTrabajo")
        @JsonManagedReference
        private List<OrdenServicio> ordenServicios;




    }


