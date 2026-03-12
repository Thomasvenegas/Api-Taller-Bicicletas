package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //listar usuarios
    public List<Usuario> listarUsuarios(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    //buscar por id
    public Optional<Usuario> buscarUsuarioId(Long id){
        return usuarioRepository.findById(id);
    }

    //crear usuario
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //eliminar usuario
    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    //crear orden de trabajo



}
