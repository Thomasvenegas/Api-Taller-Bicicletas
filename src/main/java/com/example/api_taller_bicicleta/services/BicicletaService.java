package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.repository.BicicletaRepository;
import com.example.api_taller_bicicleta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //buscar bicicletas
    @Transactional(readOnly = true)
    public List<Bicicleta> listaBicicletas(){
        return bicicletaRepository.findAll();
    }

    //buscar bicicletas por id
    @Transactional(readOnly = true)
    public Optional<Bicicleta> bicicletaId(Long Id){
        return bicicletaRepository.findById(Id);
    }

    //crear bicicleta
    public Bicicleta crearBicicleta(Bicicleta bicicleta){
        return bicicletaRepository.save(bicicleta);

    }

    //eliminar Bicicleta
    @Transactional
    public void eliminarBicicleta(Long id){
        bicicletaRepository.deleteById(id);
    }

    //Asignar un usuario a una bicicleta
    @Transactional
    public Optional<Bicicleta> asignarBicicleta(Long idBicicleta, Long idUsuario){

        Optional<Usuario> o = usuarioRepository.findById(idUsuario);
        Optional<Bicicleta> b = bicicletaRepository.findById(idBicicleta);

        if (o.isPresent() && b.isPresent()){

            Bicicleta bicicleta = b.get();
            bicicleta.setUsuario(o.get());

            return Optional.of(bicicletaRepository.save(bicicleta));
        }

        return Optional.empty();
    }


    //Desvincular usuario de bicicleta
    @Transactional
    public Optional<Bicicleta> desvincularUsuario(Long idBicicleta, Long idUsuario){

        Optional<Usuario> o = usuarioRepository.findById(idUsuario);
        Optional<Bicicleta> b = bicicletaRepository.findById(idBicicleta);

        if (o.isPresent() && b.isPresent()){

            Bicicleta bicicleta = b.get();

            bicicleta.desvincularUsuario();
            return Optional.of(bicicletaRepository.save(bicicleta));
        }

        return Optional.empty();
    }


}
