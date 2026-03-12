package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.OrdenTrabajo;
import com.example.api_taller_bicicleta.services.OrdenTrabajoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/OrdenTrabajo")
public class OrdenTrabajoController {

    private final OrdenTrabajoService ordenTrabajoService;

    public OrdenTrabajoController(OrdenTrabajoService ordenTrabajoService) {
        this.ordenTrabajoService = ordenTrabajoService;
    }

    @GetMapping
    public List<OrdenTrabajo> ordenTrabajoList(){
        return ordenTrabajoService.listarOrdenesTrabajo();
    }

    @PostMapping
    public OrdenTrabajo crearOrdenTrabajo(@RequestBody OrdenTrabajo ordenTrabajo){

        return ordenTrabajoService.crearOrden(ordenTrabajo);

    }
}
