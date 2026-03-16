package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.OrdenServicio;
import com.example.api_taller_bicicleta.services.OrdenServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orden-servicio")
public class OrdenServicioController {

    @Autowired
    public OrdenServicioService ordenServicioService;

    //crear
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody OrdenServicio ordenServicio){
        return ordenServicioService.crear(ordenServicio);
    }



    //modificar
    @PostMapping("/modificar")
    public ResponseEntity<?> modificar(@RequestBody OrdenServicio ordenServicio){
        return ordenServicioService.modificar(ordenServicio);
    }

    //eliminar
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        return ordenServicioService.eliminar(id);
    }
}
