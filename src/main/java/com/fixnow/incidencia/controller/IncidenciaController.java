package com.fixnow.incidencia.controller;
import com.fixnow.incidencia.model.Incidencia;
import com.fixnow.incidencia.service.IncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidencias")
public class IncidenciaController {
    public final IncidenciaService incidenciaService;

    public IncidenciaController (IncidenciaService incidenciaService){
        this.incidenciaService = incidenciaService;
    }

    //Obtener un listado con las incidencias que se crearon
    @GetMapping
    public List<Incidencia> listar(){
        return incidenciaService.listar();
    }

    //Obtener la incidencia exclusiva por su ID.
    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> buscarPorId(@PathVariable Long id){
        Incidencia incidencia = incidenciaService.buscarPorId(id);
        if(incidencia == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencia);
    }

    //Se crea y se guarda en el post
    @PostMapping
    public ResponseEntity<Incidencia> guardar(@Valid @RequestBody Incidencia incidencia){
        Incidencia nueva = incidenciaService.guardar(incidencia);
        return ResponseEntity.status(201).body(nueva);
    }

    //Se actualiza la incidencia deseada por ID
    @PutMapping("/{id}")
    public Incidencia actualizar(@PathVariable Long id, @Valid @RequestBody Incidencia incidencia){
        return incidenciaService.actualizar(id, incidencia);
    }

    //Se Elimina la incidencia deseada por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        incidenciaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    //Obtener la incidencia exclusiva por su Estado.
    @GetMapping("/estado/{estado}")
    public List<Incidencia> listarPorEstado(@PathVariable String estado){
        return incidenciaService.buscarPorEstado(estado);
    }
}
