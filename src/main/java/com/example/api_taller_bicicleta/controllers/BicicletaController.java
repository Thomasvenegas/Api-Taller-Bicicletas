package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.services.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> registraBicicleta(@RequestBody Bicicleta bicicleta){
        bicicletaService.crearBicicleta(bicicleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(bicicleta);
    }

    //eliminar bicileta
    @DeleteMapping("/{id}")
    public void eliminarBicicleta(@PathVariable Long id){
        bicicletaService.eliminarBicicleta(id);
    }

    //asignar bicicleta
    @PutMapping("/{idBicicleta}/usuario/{idUsuario}")
    public ResponseEntity<?> asignarBicicleta(@PathVariable Long idBicicleta,
                                              @PathVariable Long idUsuario){

        Optional<Bicicleta> bicicleta = bicicletaService.asignarBicicleta(idBicicleta, idUsuario);

        if (bicicleta.isPresent()){
            return ResponseEntity.ok(bicicleta.get());
        }

        return ResponseEntity.notFound().build();
    }

    //Desvincular usuario
    @PostMapping("/desvincular-usuario")
    public ResponseEntity<?> desvincularUsuario(@RequestParam Long idBicicleta,
                                                @RequestParam Long idUsuario){

        Optional<Bicicleta> bicicleta = bicicletaService.desvincularUsuario(idBicicleta, idUsuario);

        if (bicicleta.isPresent()){
            return ResponseEntity.ok(bicicleta.get());
        }

        return ResponseEntity.notFound().build();
    }
}
