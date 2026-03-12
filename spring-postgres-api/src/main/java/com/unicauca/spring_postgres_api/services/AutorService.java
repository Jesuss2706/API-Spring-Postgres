package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Autor;

import java.util.List;

public interface AutorService {

    List<Autor> getAutors();
    Autor getAutor(Long id);
    Autor createAutor(Autor autor);
    Autor updateAutor(Long id, Autor autor);
    boolean deleteAutor(Long id);

}
