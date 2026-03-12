package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.services.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/bicicleta")
public class BicicletaController {
    @Autowired
    private  BicicletaService bicicletaService;


    //obtener bicicletas
    @GetMapping
    public List<Bicicleta> obtenerBicicletas(){
        return bicicletaService.listaBicicletas();
    }

    //obtener bicicleta por id
    @GetMapping("/{id}")
    public Optional<Bicicleta> obtenerBicicletaId(@PathVariable Long id){
        return bicicletaService.bicicletaId(id);
    }

    //registrar bicicleta
    @PostMapping
    public ResponseEntity<Bicicleta> registraBicicleta(@RequestBody Bicicleta bicicleta){
        return bicicletaService.crearBicicleta(bicicleta);
    }

    //eliminar bicleta
    @DeleteMapping("/{id}")
    public void eliminarBicicleta(@PathVariable Long id){
        bicicletaService.eliminarBicicleta(id);
    }

    //asignar bicicleta
    @PostMapping("asignarUsuario/{idBicicleta}/{idUsuario}")
    public ResponseEntity<?> asignarBicicleta(@PathVariable Long idBicicleta, @PathVariable Long idUsuario){
        return bicicletaService.asignarBicicleta(idBicicleta, idUsuario);
    }

}
