package com.fixnow.incidencia.controller;
import com.fixnow.incidencia.model.Incidencia;
import com.fixnow.incidencia.service.IncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;// Activa la validación automática de los datos recibidos en el request como @NotBlank, @Pattern, en el modelo Incidencia.
import org.springframework.web.bind.annotation.*;// Importa las anotaciones necesarias para definir endpoints REST (GET, POST, PUT, DELETE, etc.)

import java.util.List;
import java.util.Map;// Permite crear estructuras tipo clave-valor como el "JSON" para respuestas personalizadas

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

    // Busca una incidencia por su ID.
    // Si la incidencia existe, retorna 200 OK con los datos.
    // Si no existe, retorna 404 Not Found con un mensaje de error en formato JSON.
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Incidencia incidencia = incidenciaService.buscarPorId(id);

        if (incidencia == null) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "Incidencia no encontrada"));
        }

        return ResponseEntity.ok(incidencia);
    }

    //Se registra una nueva incidencia en el sistema y valida automaticamente los datos que recibe,
    //luego le pasa la responsabilidad de creacion al servicio.
    @PostMapping
    public ResponseEntity<Incidencia> guardar(@Valid @RequestBody Incidencia incidencia){
        Incidencia nueva = incidenciaService.guardar(incidencia);
        return ResponseEntity.status(201).body(nueva);
    }

    //Se Actualiza una incidencia que existe según su ID.
    // Recibe los nuevos datos en el request y pasa la responsabilidad de actualización al servicio.
    @PutMapping("/{id}")
    public Incidencia actualizar(@PathVariable Long id, @Valid @RequestBody Incidencia incidencia){
        return incidenciaService.actualizar(id, incidencia);
    }

    //Se elimina la incidencia deseada por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        incidenciaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    //Obtiene una lista de incidencias filtradas por estado.
    @GetMapping("/estado/{estado}")
    public List<Incidencia> listarPorEstado(@PathVariable String estado){
        return incidenciaService.buscarPorEstado(estado);
    }
}
