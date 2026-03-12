package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Libro;
import com.unicauca.spring_postgres_api.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro getLibro(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    @Override
    public Libro createLibro(Libro libro) {

        if(libro.getTitulo() == null || libro.getTitulo().isEmpty()){
            throw new RuntimeException("El título del libro es obligatorio");
        }

        return libroRepository.save(libro);
    }

    @Override
    public Libro updateLibro(Long id, Libro libro) {

        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));

        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setFecha(libro.getFecha());
        libroExistente.setGenero(libro.getGenero());
        libroExistente.setPaginas(libro.getPaginas());
        libroExistente.setPersona(libro.getPersona());
        libroExistente.setAutor(libro.getAutor());

        return libroRepository.save(libroExistente);
    }

    @Override
    public boolean deleteLibro(Long id) {

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));

        libroRepository.delete(libro);
        return true;
    }
}