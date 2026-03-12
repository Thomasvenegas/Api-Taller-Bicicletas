package com.example.api_taller_bicicleta.services;

import com.example.api_taller_bicicleta.entity.OrdenTrabajo;
import com.example.api_taller_bicicleta.entity.Usuario;
import com.example.api_taller_bicicleta.enums.EstadoOrden;
import com.example.api_taller_bicicleta.repository.OrdenTrabajoRepository;
import com.example.api_taller_bicicleta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenTrabajoService {

    private final OrdenTrabajoRepository ordenTrabajoRepository;
    private final UsuarioRepository usuarioRepository;

    public OrdenTrabajoService(OrdenTrabajoRepository ordenTrabajoRepository, UsuarioRepository usuarioRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //listar ordenes de trabajo
    public List<OrdenTrabajo> listarOrdenesTrabajo(){
        return ordenTrabajoRepository.findAll();
    }

    //crearOrden
    public OrdenTrabajo crearOrden(OrdenTrabajo ordenTrabajo){
        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    // cambiar estado
    public void marcarComoEntregado(Long id) {

        OrdenTrabajo orden = ordenTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setEstado(EstadoOrden.ENTREGADO);

        ordenTrabajoRepository.save(orden);
    }

    //Asignar Mecanico

    public OrdenTrabajo asignarMecanico(Long ordenId, Long mecanicoId) {

        OrdenTrabajo orden = ordenTrabajoRepository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        Usuario mecanico = usuarioRepository.findById(mecanicoId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //  Validamos que realmente sea mecánico
        if (!mecanico.esMecanico()) {
            throw new RuntimeException("El usuario no es mecánico");
        }

        orden.setMecanico(mecanico);

        return ordenTrabajoRepository.save(orden);
    }










}
