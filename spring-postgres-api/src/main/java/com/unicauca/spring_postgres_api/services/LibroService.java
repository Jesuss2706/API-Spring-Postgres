package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Libro;

import java.util.List;

public interface LibroService {

    List<Libro> getLibros();
    Libro getLibro(Long id);
    Libro createLibro(Libro libro);
    Libro updateLibro(Long id, Libro libro);
    boolean deleteLibro(Long id);
}
