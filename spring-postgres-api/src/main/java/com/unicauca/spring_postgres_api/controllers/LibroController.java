package com.unicauca.spring_postgres_api.controllers;

import com.unicauca.spring_postgres_api.modules.Libro;
import com.unicauca.spring_postgres_api.services.LibroService;
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
@RequestMapping("Libro")
public class LibroController {

    @Autowired
    @Lazy
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> getLibros(){
        List<Libro> libros = libroService.getLibros();
        if(libros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLibro(@PathVariable Long id){
        Libro libro = libroService.getLibro(id);
        if(libro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el libro con id: "+id);
        }
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<?> createLibro(@Valid @RequestBody Libro libro, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Libro newLibro = libroService.createLibro(libro);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newLibro.getId())
                .toUri();

        return ResponseEntity.created(location).body(newLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibro(@PathVariable Long id, @Valid @RequestBody Libro libro, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Libro updLibro = libroService.updateLibro(id, libro);
        return ResponseEntity.ok(updLibro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id){
        boolean deleted = libroService.deleteLibro(id);

        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el libro con id: "+id);
        }

        return ResponseEntity.noContent().build();
    }
}