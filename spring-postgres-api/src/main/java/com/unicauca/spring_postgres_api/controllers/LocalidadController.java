package com.unicauca.spring_postgres_api.controllers;

import com.unicauca.spring_postgres_api.modules.Autor;
import com.unicauca.spring_postgres_api.modules.Localidad;
import com.unicauca.spring_postgres_api.services.LocalidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("Localidad")
public class LocalidadController {

    @Autowired
    @Lazy
    private LocalidadService localidadService;

    @GetMapping
    public ResponseEntity<List<Localidad>> getLocalidades(){
        List<Localidad> localidades = localidadService.getLocalidades();
        if(localidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(localidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocalidad(@PathVariable Long id){
        Localidad localidad = localidadService.getLocalidad(id);
        if(localidad == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el autor en el id: "+id);
        }
        return ResponseEntity.ok(localidad);
    }

    @PostMapping
    public ResponseEntity<?> createLocalidad(@Valid @RequestBody Localidad localidad, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Localidad newLocalidad = localidadService.createLocalidad(localidad);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newLocalidad.getId())
                .toUri();

        return ResponseEntity.created(location).body(newLocalidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocalidad(@PathVariable Long id, @Valid @RequestBody Localidad localidad, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Localidad updLocalidad = localidadService.updateLocalidad(id, localidad);
        return ResponseEntity.ok(updLocalidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocalidad(@PathVariable Long id){
        boolean deleted = localidadService.deleteLocalidad(id);

        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la localidad con id: "+id);
        }

        return ResponseEntity.noContent().build();
    }
}