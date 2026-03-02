package com.example.api_taller_bicicleta.controllers;


import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    //listar usuarios
    @GetMapping
    public List<Usuario> usuarios(){
        return usuarioService.listarUsuarios();
    }
    //buscar usuario por id
    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarUsuarioId(id);
    }

    //crear usuario
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
