package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.OrdenServicio;
import com.example.api_taller_bicicleta.repository.OrdenServicioRepository;
import com.example.api_taller_bicicleta.repository.ServicioRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdenServicioService {

    @Autowired
    public OrdenServicioRepository ordenServicioRepository;

    //crear orden servicio
    public ResponseEntity<?> crear(OrdenServicio ordenServicio){

        ordenServicioRepository.save(ordenServicio);

        return ResponseEntity.status(HttpStatus.CREATED).body(ordenServicio);

    }

    //modificar
    public ResponseEntity<?> modificar(OrdenServicio ordenServicio){

        Optional<OrdenServicio> o = ordenServicioRepository.findById(ordenServicio
                .getId());

        if (o.isPresent()){

            OrdenServicio orden = o.get();

            orden.setServicio(ordenServicio.getServicio());
            orden.setEstado(ordenServicio.getEstado());

            ordenServicioRepository.save(orden);

            return ResponseEntity.ok(orden);


        }

        return ResponseEntity.notFound().build();
    }

    //eliminar
    public ResponseEntity<?> eliminar(Long id){

        Optional<OrdenServicio> o = ordenServicioRepository.findById(id);

        if (o.isPresent()){
            ordenServicioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


}
