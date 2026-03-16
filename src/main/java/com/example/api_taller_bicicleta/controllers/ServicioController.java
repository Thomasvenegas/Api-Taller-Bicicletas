package com.example.api_taller_bicicleta.controllers;

import com.example.api_taller_bicicleta.entity.Servicio;
import com.example.api_taller_bicicleta.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/servicio")
public class ServicioController {

    @Autowired
    public ServicioService servicioService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return servicioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return servicioService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Servicio servicio){
        return servicioService.crear(servicio);
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody Servicio servicio){
        return servicioService.modificar(servicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        return servicioService.eliminar(id);
    }

}
