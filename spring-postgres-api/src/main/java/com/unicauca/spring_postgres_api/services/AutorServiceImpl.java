package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Autor;
import com.unicauca.spring_postgres_api.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @Override
    public List<Autor> getAutors() {
        return autorRepository.findAll();
    }

    @Override
    public Autor getAutor(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
    }

    @Override
    public Autor createAutor(Autor autor) {

        if (autor.getNombre() == null || autor.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre del autor es obligatorio");
        }

        return autorRepository.save(autor);
    }

    @Override
    public Autor updateAutor(Long id, Autor autor) {

        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));

        autorExistente.setNombre(autor.getNombre());
        autorExistente.setApellido(autor.getApellido());
        autorExistente.setBiografia(autor.getBiografia());

        return autorRepository.save(autorExistente);
    }

    @Override
    public boolean deleteAutor(Long id) {

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));

        autorRepository.delete(autor);
        return true;
    }
}