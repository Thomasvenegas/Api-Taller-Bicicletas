package com.example.api_taller_bicicleta.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String password;
    private String rol;


    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Bicicleta> bicicletas;

    @OneToMany(mappedBy = "usuario")
    private List<OrdenTrabajo> ordenTrabajo;


    //es mecanico
    public boolean esMecanico(){
        return "Mecanico".equalsIgnoreCase(this.rol);
    }

    //es cliente
    public boolean esCliente(){
        return "Cliente".equalsIgnoreCase(this.rol);
    }

    //bicicletas
    public List<Bicicleta> listaBicicletas(){
        return this.bicicletas;
    }
}
