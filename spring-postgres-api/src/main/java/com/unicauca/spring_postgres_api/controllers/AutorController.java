package com.unicauca.spring_postgres_api.controllers;


import com.unicauca.spring_postgres_api.modules.Autor;
import com.unicauca.spring_postgres_api.services.AutorService;
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
@RequestMapping("Autor")
public class AutorController {

    @Autowired
    @Lazy
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> getAutors(){
        List<Autor> autors = autorService.getAutors();
        if (autors.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAutor(@PathVariable Long id){
        Autor autor = autorService.getAutor(id);
        if(autor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el autor en el id: "+id);
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<?> createAutor(@Valid @RequestBody Autor autor, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Autor newAutor = autorService.createAutor(autor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(newAutor.getId())
                .toUri();

        return ResponseEntity.created(location).body(newAutor);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAutor(@PathVariable Long id, @Valid @RequestBody Autor autor, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
                            .toList()
            );
        }

        Autor updAutor = autorService.updateAutor(id, autor);
        if(updAutor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el autor en el id: "+id);
        }
        return ResponseEntity.ok(updAutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAutor(@PathVariable Long id){
        boolean deleted = autorService.deleteAutor(id);

        if (!deleted){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el autor con el id: "+id);
        }
        return ResponseEntity.noContent().build();
    }
}
