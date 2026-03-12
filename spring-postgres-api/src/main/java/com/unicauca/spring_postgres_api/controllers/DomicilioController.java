package com.unicauca.spring_postgres_api.controllers;

import com.unicauca.spring_postgres_api.modules.Domicilio;
import com.unicauca.spring_postgres_api.services.DomicilioService;
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
@RequestMapping("Domicilio")
public class DomicilioController {

    @Autowired
    @Lazy
    private DomicilioService domicilioService;

    @GetMapping
    public ResponseEntity<List<Domicilio>> getDomicilios(){
        List<Domicilio> domicilios = domicilioService.getDomicilios();
        if(domicilios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(domicilios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDomicilio(@PathVariable Long id){
        Domicilio domicilio = domicilioService.getDomicilio(id);
        if(domicilio == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el domicilio con id: "+id);
        }
        return ResponseEntity.ok(domicilio);
    }

    @PostMapping
    public ResponseEntity<?> createDomicilio(@Valid @RequestBody Domicilio domicilio, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Domicilio newDomicilio = domicilioService.createDomicilio(domicilio);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDomicilio.getId())
                .toUri();

        return ResponseEntity.created(location).body(newDomicilio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDomicilio(@PathVariable Long id, @Valid @RequestBody Domicilio domicilio, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Domicilio updDomicilio = domicilioService.updateDomicilio(id, domicilio);
        return ResponseEntity.ok(updDomicilio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDomicilio(@PathVariable Long id){
        boolean deleted = domicilioService.deleteDomicilio(id);

        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el domicilio con id: "+id);
        }

        return ResponseEntity.noContent().build();
    }
}