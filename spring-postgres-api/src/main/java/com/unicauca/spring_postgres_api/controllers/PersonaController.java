package com.unicauca.spring_postgres_api.controllers;

import com.unicauca.spring_postgres_api.modules.Localidad;
import com.unicauca.spring_postgres_api.modules.Persona;
import com.unicauca.spring_postgres_api.services.PersonaService;
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
@RequestMapping("Persona")
public class PersonaController {

    @Autowired
    @Lazy
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> getPersonas(){
        List<Persona> personas = personaService.getPersonas();
        if(personas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersona(@PathVariable Long id){
        Persona persona = personaService.getPersona(id);
        if(persona == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el autor en el id: "+id);
        }
        return ResponseEntity.ok(persona);
    }

    @PostMapping
    public ResponseEntity<?> createPersona(@Valid @RequestBody Persona persona, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Persona newPersona = personaService.createPersona(persona);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPersona.getId())
                .toUri();

        return ResponseEntity.created(location).body(newPersona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id){
        boolean deleted = personaService.deletePersona(id);

        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la persona con id: "+id);
        }

        return ResponseEntity.noContent().build();
    }
}