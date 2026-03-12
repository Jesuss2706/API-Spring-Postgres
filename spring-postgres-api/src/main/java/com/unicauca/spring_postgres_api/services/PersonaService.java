package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> getPersonas();
    Persona getPersona(Long id);
    Persona createPersona(Persona persona);
    Persona updatePersona(Long id, Persona persona);
    boolean deletePersona(Long id);
}
