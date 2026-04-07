package com.fixnow.incidencia.service;

import com.fixnow.incidencia.model.Incidencia;
import com.fixnow.incidencia.repository.IncidenciaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate; //Para que genere automaticamente la fecha de registro de la incidencia cuando se crea.

@Service
public class IncidenciaService {
    private final IncidenciaRepository incidenciaRepository;

    public  IncidenciaService(IncidenciaRepository incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }

    public List<Incidencia>listar(){
        return incidenciaRepository.findAll();
    }

    //Este metodo Busca la incidencia por id.
    //Si existe, la va a devolver, si no va a lanzar un error sumado con el mensaje "Incidencia no encontrada".
    public Incidencia buscarPorId(Long id){
        return incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }

    public Incidencia guardar(@NonNull Incidencia incidencia){
        incidencia.setFechaRegistro(LocalDate.now().toString());
        return incidenciaRepository.save(incidencia);
    }

    public boolean existePorId(Long id){
        return incidenciaRepository.existsById(id);
    }

    public Incidencia actualizar(Long id, Incidencia nuevaIncidencia){
        Incidencia incidenciaExistente = buscarPorId(id);

        if(incidenciaExistente != null){
            incidenciaExistente.setTitulo(nuevaIncidencia.getTitulo());
            incidenciaExistente.setDescripcion(nuevaIncidencia.getDescripcion());
            incidenciaExistente.setEstado(nuevaIncidencia.getEstado());
            incidenciaExistente.setPrioridad(nuevaIncidencia.getPrioridad());
            incidenciaExistente.setUsuarioReportante(nuevaIncidencia.getUsuarioReportante());



            return incidenciaRepository.save(incidenciaExistente);
        }
        return null;
    }

    public void eliminarPorId(Long id){
        incidenciaRepository.deleteById(id);
    }

    //Creamos "buscarPorEstado" para listar las incidencias por estado, por ejemplo: abiertas, en proceso o cerradas.
    public List<Incidencia> buscarPorEstado(String estado){
        return incidenciaRepository.findByEstado(estado);
    }
}
