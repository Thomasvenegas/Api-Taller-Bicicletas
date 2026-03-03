package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.services.BicicletaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/bicicleta")
public class BicicletaController {

    private final BicicletaService bicicletaService;

    public BicicletaController(BicicletaService bicicletaService) {
        this.bicicletaService = bicicletaService;
    }

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
    public Bicicleta registraBicicleta(@RequestBody Bicicleta bicicleta){
        return bicicletaService.crearBicicleta(bicicleta);
    }

    //eliminar bicleta
    @DeleteMapping("/{id}")
    public void eliminarBicicleta(@PathVariable Long id){
        bicicletaService.eliminarBicicleta(id);
    }

    //obtener usuario de una bicicleta por id de bicicleta
    @GetMapping("/{id}/usuario")
    public Optional<Usuario> obtenerUsuarioDeBicicleta(@PathVariable Long id){
        return bicicletaService.obtenerPropietario(id);
    }

}
