package com.example.api_taller_bicicleta.services;
import com.example.api_taller_bicicleta.entity.Servicio;
import com.example.api_taller_bicicleta.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ServicioService {

    @Autowired
    public ServicioRepository servicioRepository;

    @Transactional(readOnly = true)
    public List<Servicio> listar(){
        return servicioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Servicio> buscarPorId(Long id){
        return servicioRepository.findById(id);
    }

    //crear servicio
    @Transactional
    public Servicio crear(Servicio servicio){

        // Si ya tiene ID, no debería crearse
        if (servicio.getId() != null && servicioRepository.existsById(servicio.getId())){
            return null;
        }

        return servicioRepository.save(servicio);
    }

    //modificar
    @Transactional
    public Optional<Servicio> modificar(Servicio servicio){

        Optional<Servicio> s = servicioRepository.findById(servicio.getId());

        if (s.isPresent()){
            Servicio serv1 = s.get();

            serv1.setDescripcion(servicio.getDescripcion());
            serv1.setNombre(servicio.getNombre());
            serv1.setPrecio(servicio.getPrecio());

            return Optional.of(servicioRepository.save(serv1));
        }

        return Optional.empty();
    }

    //eliminar
    @Transactional
    public boolean eliminar(Long id){

        if (servicioRepository.existsById(id)){
            servicioRepository.deleteById(id);
            return true;
        }

        return false;
    }
}


