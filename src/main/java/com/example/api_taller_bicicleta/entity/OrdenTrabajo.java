package com.example.api_taller_bicicleta.entity;
import com.example.api_taller_bicicleta.enums.EstadoOrden;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


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


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Date getFechaIngreso() {
                return fechaIngreso;
        }

        public void setFechaIngreso(Date fechaIngreso) {
                this.fechaIngreso = fechaIngreso;
        }

        public Date getFechaEstimada() {
                return fechaEstimada;
        }

        public void setFechaEstimada(Date fechaEstimada) {
                this.fechaEstimada = fechaEstimada;
        }

        public EstadoOrden getEstado() {
                return estado;
        }

        public void setEstado(EstadoOrden estado) {
                this.estado = estado;
        }

        public String getObservaciones() {
                return observaciones;
        }

        public void setObservaciones(String observaciones) {
                this.observaciones = observaciones;
        }

        public Bicicleta getBicicleta() {
                return bicicleta;
        }

        public void setBicicleta(Bicicleta bicicleta) {
                this.bicicleta = bicicleta;
        }

        public Usuario getCliente() {
                return cliente;
        }

        public void setCliente(Usuario cliente) {
                this.cliente = cliente;
        }

        public Usuario getMecanico() {
                return mecanico;
        }

        public void setMecanico(Usuario mecanico) {
                this.mecanico = mecanico;
        }
}


