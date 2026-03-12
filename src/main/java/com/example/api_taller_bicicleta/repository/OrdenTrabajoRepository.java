package com.example.api_taller_bicicleta.repository;

import com.example.api_taller_bicicleta.entity.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
    OrdenTrabajo estado(String estado);
}
