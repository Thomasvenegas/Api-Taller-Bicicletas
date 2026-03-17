package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.OrdenServicio;
import com.example.api_taller_bicicleta.repository.OrdenServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrdenServicioService {

    @Autowired
    public OrdenServicioRepository ordenServicioRepository;

    //crear orden servicio
    public OrdenServicio crear(OrdenServicio ordenServicio){

        return ordenServicioRepository.save(ordenServicio);

    }

    //modificar
    public Optional<OrdenServicio> modificar(OrdenServicio ordenServicio){

        Optional<OrdenServicio> o = ordenServicioRepository
                .findById(ordenServicio.getId());

        if (o.isPresent()){

            OrdenServicio orden = o.get();

            orden.setServicio(ordenServicio.getServicio());
            orden.setEstado(ordenServicio.getEstado());

            return Optional.of(ordenServicioRepository.save(orden));
        }

        return Optional.empty();
    }
    //eliminar
    public Optional<Boolean> eliminar(Long id){

        Optional<OrdenServicio> o = ordenServicioRepository.findById(id);

        if (o.isPresent()){
            ordenServicioRepository.deleteById(id);
            return Optional.of(true);
        }

        return Optional.empty();
    }


}
