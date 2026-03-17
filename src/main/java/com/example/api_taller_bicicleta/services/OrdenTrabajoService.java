package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.Bicicleta;
import com.example.api_taller_bicicleta.entity.OrdenTrabajo;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.enums.EstadoOrden;
import com.example.api_taller_bicicleta.repository.BicicletaRepository;
import com.example.api_taller_bicicleta.repository.OrdenTrabajoRepository;
import com.example.api_taller_bicicleta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrdenTrabajoService {
    @Autowired
    private  OrdenTrabajoRepository ordenTrabajoRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;
    @Autowired
    private BicicletaRepository bicicletaRepository;



    //listar ordenes de trabajo
    @Transactional(readOnly = true)
    public List<OrdenTrabajo> listarOrdenesTrabajo(){
        return ordenTrabajoRepository.findAll();
    }

    //ordenTrabajo por Id
    @Transactional(readOnly = true)
    public Optional<OrdenTrabajo> buscarPorId(Long id) {return ordenTrabajoRepository.findById(id);}
    //crearOrden
    @Transactional
    public Optional<OrdenTrabajo> crearOrden(OrdenTrabajo ordenTrabajo,
                                             Long idMecanico,
                                             Long idBicicleta){

        Optional<Usuario> m = usuarioRepository.findById(idMecanico);
        Optional<Bicicleta> b = bicicletaRepository.findById(idBicicleta);

        if (m.isPresent() && b.isPresent()){

            Usuario mecanico = m.get();
            Bicicleta bicicleta = b.get();
            Usuario cliente = bicicleta.getUsuario();

            if (mecanico.esMecanico()){

                ordenTrabajo.setMecanico(mecanico);
                ordenTrabajo.setBicicleta(bicicleta);
                ordenTrabajo.setCliente(cliente);

                return Optional.of(ordenTrabajoRepository.save(ordenTrabajo));
            }
        }

        return Optional.empty();
    }

    // marcar como entregado
    @Transactional
    public void marcarComoEntregado(Long id) {

        OrdenTrabajo orden = ordenTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setEstado(EstadoOrden.ENTREGADO);

        ordenTrabajoRepository.save(orden);
    }

    //marcar como EN_PROCESO
    @Transactional
    public void marcarEnProceso(Long id) {

        OrdenTrabajo orden = ordenTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setEstado(EstadoOrden.EN_PROCESO);
        ordenTrabajoRepository.save(orden);
    }

    //Elimianr OrdenDeTrabajo
    @Transactional
    public void eliminarOrdenDeTrabajo(Long id){


            ordenTrabajoRepository.deleteById(id);


    }















}
