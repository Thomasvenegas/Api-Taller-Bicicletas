package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.TallerConfig;
import com.example.api_taller_bicicleta.repository.TallerConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class TallerConfigService {

    @Autowired
    TallerConfigRepository tallerConfigRepository;

    //listar
    @Transactional(readOnly = true)
    public List<TallerConfig> listar(){
        return tallerConfigRepository.findAll();
    }

    //modificar capacidad maxima
    public ResponseEntity<?> modificarCapacidadMaxima(Long capacidadMaxima){
        Long id = 1L;
        Optional<TallerConfig> tallerConfig = tallerConfigRepository.findById(id);

        if(tallerConfig.isPresent()) {
            TallerConfig o = tallerConfig.get();

            o.setCapacidadMaxima(capacidadMaxima);

            tallerConfigRepository.save(o);

            return ResponseEntity.ok(o);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Boolean> tallerLleno() {

        Long id = 1L;

        Optional<TallerConfig> tallerConfig = tallerConfigRepository.findById(id);

        if (tallerConfig.isPresent()) {
            TallerConfig o = tallerConfig.get();
            return ResponseEntity.ok(o.getTallerLleno());
        }

        return ResponseEntity.notFound().build();
    }


}
