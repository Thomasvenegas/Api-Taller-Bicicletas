package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.Servicio;
import com.example.api_taller_bicicleta.repository.ServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    public ServicioRepository servicioRepository;


    @Transactional
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(servicioRepository.findAll());
    }

    @Transactional
    public ResponseEntity<?> buscarPorId(Long id){
        return servicioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //crear servicio
    @Transactional
    public ResponseEntity<?> crear(Servicio servicio){

        Optional<Servicio> s = servicioRepository.findById(servicio.getId());

        if (s.isPresent()){
            servicioRepository.save(servicio);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.badRequest().build();

    }
    
    //modificar
    @Transactional
    public ResponseEntity<?> modificar(Servicio servicio){
        
        Optional<Servicio> s = servicioRepository.findById(servicio.getId());
        
        if (s.isPresent()){
            Servicio serv1 = s.get();
            
            serv1.setDescripcion(servicio.getDescripcion());
            serv1.setNombre(servicio.getNombre());
            serv1.setPrecio(servicio.getPrecio());
            
            return ResponseEntity.ok(serv1);
        }
        
        return ResponseEntity.notFound().build();
        
    }
    
    
    //eliminar
    @Transactional
    public ResponseEntity<?> eliminar(Long id){

        Optional<Servicio> s = servicioRepository.findById(id);

        if (s.isPresent()){

            servicioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.notFound().build();
        

    }




}
