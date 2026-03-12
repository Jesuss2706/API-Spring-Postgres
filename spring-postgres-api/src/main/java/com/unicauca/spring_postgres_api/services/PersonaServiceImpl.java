package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Persona;
import com.unicauca.spring_postgres_api.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> getPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Persona getPersona(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));
    }

    @Override
    public Persona createPersona(Persona persona) {

        if(persona.getNombre() == null || persona.getNombre().isEmpty()){
            throw new RuntimeException("El nombre es obligatorio");
        }

        if(persona.getApellido() == null || persona.getApellido().isEmpty()){
            throw new RuntimeException("El apellido es obligatorio");
        }

        if(persona.getDni() == null){
            throw new RuntimeException("El DNI es obligatorio");
        }

        return personaRepository.save(persona);
    }

    @Override
    public Persona updatePersona(Long id, Persona persona) {

        Persona personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));

        personaExistente.setNombre(persona.getNombre());
        personaExistente.setApellido(persona.getApellido());
        personaExistente.setDni(persona.getDni());
        personaExistente.setDomicilio(persona.getDomicilio());

        return personaRepository.save(personaExistente);
    }

    @Override
    public boolean deletePersona(Long id) {

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));

        personaRepository.delete(persona);
        return true;
    }
}