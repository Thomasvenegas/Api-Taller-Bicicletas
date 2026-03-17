package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.OrdenTrabajo;
import com.example.api_taller_bicicleta.services.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orden-trabajo")
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;


    //lista de ordenes de trabajo
    @GetMapping
    public List<OrdenTrabajo> ordenTrabajoList(){
        return ordenTrabajoService.listarOrdenesTrabajo();
    }

    //Buscar ordenDeTrabajo por id
    @GetMapping("/{id}")
    public Optional<OrdenTrabajo> ordenTrabajoId(@PathVariable Long id){return ordenTrabajoService.buscarPorId(id);}

    //crear orden de trabajo
    @PostMapping
    public ResponseEntity<?> crearOrden(
            @RequestBody OrdenTrabajo ordenTrabajo,
            @RequestParam Long idMecanico,
            @RequestParam Long idBicicleta) {

        Optional<OrdenTrabajo> orden = ordenTrabajoService.crearOrden(ordenTrabajo, idMecanico, idBicicleta);

        if (orden.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(orden.get());
        }

        return ResponseEntity.notFound().build();
    }

    //eliminar ordenDeTrabajoo
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){

        Optional<OrdenTrabajo> o = ordenTrabajoService.buscarPorId(id);

        if(o.isPresent()){

            ordenTrabajoService.eliminarOrdenDeTrabajo(id);

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.notFound().build();

    }





}

