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
    public List<OrdenTrabajo> listarOrdenesTrabajo(){
        return ordenTrabajoRepository.findAll();
    }

    //ordenTrabajo por Id
    public Optional<OrdenTrabajo> buscarPorId(Long id) {return ordenTrabajoRepository.findById(id);}
    //crearOrden
    public ResponseEntity<?> crearOrden(OrdenTrabajo ordenTrabajo, Long idMecanico, Long idBicicleta){

        Optional<Usuario> m = usuarioRepository.findById(idMecanico);
        Optional<Bicicleta> b = bicicletaRepository.findById(idBicicleta);


        if(m.isPresent() && b.isPresent()){

            Usuario mecanico = m.get();
            Bicicleta bicicleta = b.get();
            Usuario cliente = bicicleta.getUsuario();

            if(mecanico.esMecanico()){

                ordenTrabajo.setMecanico(mecanico);
                ordenTrabajo.setBicicleta(bicicleta);
                ordenTrabajo.setCliente(cliente);

                ordenTrabajoRepository.save(ordenTrabajo);

                return ResponseEntity.status(HttpStatus.CREATED).body(ordenTrabajo);

            } else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(Map.of(
                                "error", "Acceso Denegado",
                                "mensaje", "El usuario con ID " + idMecanico + " no tiene permisos de mecánico."
                        ));
            }

        }

        return ResponseEntity.notFound().build();
    }

    // marcar como entregado
    public void marcarComoEntregado(Long id) {

        OrdenTrabajo orden = ordenTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setEstado(EstadoOrden.ENTREGADO);

        ordenTrabajoRepository.save(orden);
    }

    //marcar como EN_PROCESO

    public void marcarEnProceso(Long id) {

        OrdenTrabajo orden = ordenTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setEstado(EstadoOrden.EN_PROCESO);
        ordenTrabajoRepository.save(orden);
    }

    //Elimianr OrdenDeTrabajo
    public ResponseEntity<?> eliminarOrdenDeTrabajo(Long id){

        Optional<OrdenTrabajo> o = ordenTrabajoRepository.findById(id);

        if(o.isPresent()){

            ordenTrabajoRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.notFound().build();

    }















}
