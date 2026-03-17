package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/usuario")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;

    //listar usuarios
    @GetMapping
    public List<Usuario> usuarios(){
        return usuarioService.listarUsuarios();
    }
    //buscar usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){

        Optional<Usuario> usuario = usuarioService.buscarUsuarioId(id);

        if(usuario.isPresent()){
            ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();


    }

    //crear usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
