package com.fixnow.incidencia.repository;

import com.fixnow.incidencia.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByEstado(String estado);
}
