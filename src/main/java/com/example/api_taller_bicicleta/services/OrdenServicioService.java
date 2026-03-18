package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.*;
import com.example.api_taller_bicicleta.repository.OrdenServicioRepository;
import com.example.api_taller_bicicleta.repository.OrdenTrabajoRepository;
import com.example.api_taller_bicicleta.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServicioService {

    @Autowired
    public OrdenServicioRepository ordenServicioRepository;

    @Autowired
    public ServicioRepository servicioRepository;

    @Autowired
    public OrdenTrabajoRepository ordenTrabajoRepository;



    //listar
    public List<OrdenServicio> listar(){

        return ordenServicioRepository.findAll();

    }

    //buscar por id
    public Optional<OrdenServicio> buscarPorId(Long id){
        return ordenServicioRepository.findById(id);
    }



    //crear orden servicio
    public OrdenServicio crear(OrdenServicio ordenServicio, Long idOrdenTrabajo, Long idServicio){

        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(idOrdenTrabajo)
                .orElseThrow(() -> new RuntimeException("Orden de trabajo no encontrada"));

        ordenServicio.setServicio(servicio);
        ordenServicio.setOrdenTrabajo(ordenTrabajo);

        return ordenServicioRepository.save(ordenServicio);
    }

    //asignar servicio
    @Transactional
    public Optional<OrdenServicio> asignarServicio(Long idOrdenServicio, Long idServicio){

        Optional<Servicio> s = servicioRepository.findById(idServicio);
        Optional<OrdenServicio> o = ordenServicioRepository.findById(idOrdenServicio);

        if (s.isPresent() && o.isPresent()){

            Servicio servicio = s.get();
            OrdenServicio ordenServicio = o.get();

            //asignamos el servicio
            ordenServicio.setServicio(servicio);




            return Optional.of(ordenServicioRepository.save(ordenServicio));
        }

        return Optional.empty();
    }


    //desvincular servicio
    @Transactional
    public Optional<OrdenServicio> desvincularServicio(Long idOrdenServicio, Long idServicio){

        Optional<Servicio> s = servicioRepository.findById(idServicio);
        Optional<OrdenServicio> o = ordenServicioRepository.findById(idOrdenServicio);

        if (s.isPresent() && o.isPresent()){

            Servicio servicio = s.get();
            OrdenServicio ordenServicio = o.get();

            //asignamos el servicio
            ordenServicio.desvincularServicio(servicio);


            return Optional.of(ordenServicioRepository.save(ordenServicio));
        }

        return Optional.empty();
    }




    //modificar
    public Optional<OrdenServicio> modificar(OrdenServicio ordenServicio){

        Optional<OrdenServicio> o = ordenServicioRepository
                .findById(ordenServicio.getId());

        if (o.isPresent()){

            OrdenServicio orden = o.get();


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
