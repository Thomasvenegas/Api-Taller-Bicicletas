package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.OrdenServicio;
import com.example.api_taller_bicicleta.services.OrdenServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orden-servicio")
public class OrdenServicioController {

    @Autowired
    public OrdenServicioService ordenServicioService;


    //listar
    @GetMapping
    public List<OrdenServicio> listar(){
        return ordenServicioService.listar();
    }


    //crear
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody OrdenServicio ordenServicio, @RequestParam Long idOrdenTrabajo,@RequestParam Long idServicio ){

        ordenServicioService.crear(ordenServicio,idOrdenTrabajo,idServicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenServicio);
    }

    //asignar Servicio
    @PostMapping("/asignar-servicio")
    public ResponseEntity<?> asignarServicio(@RequestParam Long idOrdenServicio, @RequestParam Long idServicio){

        ordenServicioService.asignarServicio(idOrdenServicio, idServicio);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }




    //modificar
    @PostMapping("/modificar")
    public ResponseEntity<?> modificar(@RequestBody OrdenServicio ordenServicio){

        Optional<OrdenServicio> orden = ordenServicioService.modificar(ordenServicio);

        if (orden.isPresent()){
            return ResponseEntity.ok(orden.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){

        Optional<Boolean> eliminado = ordenServicioService.eliminar(id);

        if (eliminado.isPresent()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
