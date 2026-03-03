package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.repository.BicicletaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {

    private final BicicletaRepository bicicletaRepository;

    public BicicletaService(BicicletaRepository bicicletaRepository) {
        this.bicicletaRepository = bicicletaRepository;
    }


    //buscar bicicletas
    public List<Bicicleta> listaBicicletas(){
        return bicicletaRepository.findAll();
    }

    //buscar bicicletas por id
    public Optional<Bicicleta> bicicletaId(Long Id){
        return bicicletaRepository.findById(Id);
    }

    //crear bicicleta
    public Bicicleta crearBicicleta(Bicicleta bicicleta){
        return bicicletaRepository.save(bicicleta);
    }

    //eliminar Bicicleta
    public void eliminarBicicleta(Long id){
         bicicletaRepository.deleteById(id);
    }

    //obtener usuario propietario por id de bicicleta
    public Optional<Usuario> obtenerPropietario(Long bicicletaId){
        return bicicletaRepository.findById(bicicletaId)
                .map(Bicicleta::getUsuario);
    }
}
