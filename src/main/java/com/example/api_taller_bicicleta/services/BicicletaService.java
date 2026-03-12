package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.repository.BicicletaRepository;
import com.example.api_taller_bicicleta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //buscar bicicletas
    public List<Bicicleta> listaBicicletas(){
        return bicicletaRepository.findAll();
    }

    //buscar bicicletas por id
    public Optional<Bicicleta> bicicletaId(Long Id){
        return bicicletaRepository.findById(Id);
    }

    //crear bicicleta
    public ResponseEntity<Bicicleta> crearBicicleta(Bicicleta bicicleta){

        bicicletaRepository.save(bicicleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(bicicleta);
    }

    //Asignar un usuario a una bicicleta
    public ResponseEntity<?> asignarBicicleta(Long idBicicleta, Long idUsuario){

        Optional<Usuario> o = usuarioRepository.findById(idUsuario);
        Optional<Bicicleta> b = bicicletaRepository.findById(idBicicleta);

        if (o.isPresent() && b.isPresent()){

            Usuario usuario = o.get();
            Bicicleta bicicleta = b.get();

            bicicleta.setUsuario(usuario);
            bicicletaRepository.save(bicicleta);

            return ResponseEntity.ok(bicicleta);

        }

        return ResponseEntity.notFound().build();

    }

    //Desasignar Bicicleta



    //eliminar Bicicleta
    public void eliminarBicicleta(Long id){
         bicicletaRepository.deleteById(id);
    }


}
