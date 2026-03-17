package com.example.api_taller_bicicleta.controllers;

import com.example.api_taller_bicicleta.entity.Servicio;
import com.example.api_taller_bicicleta.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/servicio")
public class ServicioController {

    @Autowired
    public ServicioService servicioService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(servicioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){

        Optional<Servicio> servicio = servicioService.buscarPorId(id);

        if (servicio.isPresent()){
            return ResponseEntity.ok(servicio.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Servicio servicio){

        Servicio nuevo = servicioService.crear(servicio);

        if (nuevo != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody Servicio servicio){

        Optional<Servicio> actualizado = servicioService.modificar(servicio);

        if (actualizado.isPresent()){
            return ResponseEntity.ok(actualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){

        boolean eliminado = servicioService.eliminar(id);

        if (eliminado){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}