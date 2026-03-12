package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Localidad;
import com.unicauca.spring_postgres_api.repository.LocalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadServiceImpl implements LocalidadService {

    private final LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(LocalidadRepository localidadRepository){
        this.localidadRepository = localidadRepository;
    }

    @Override
    public List<Localidad> getLocalidades() {
        return localidadRepository.findAll();
    }

    @Override
    public Localidad getLocalidad(Long id) {
        return localidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localidad no encontrada con id: " + id));
    }

    @Override
    public Localidad createLocalidad(Localidad localidad) {

        if(localidad.getDenominacion() == null || localidad.getDenominacion().isEmpty()){
            throw new RuntimeException("La denominación de la localidad es obligatoria");
        }

        return localidadRepository.save(localidad);
    }

    @Override
    public Localidad updateLocalidad(Long id, Localidad localidad) {

        Localidad localidadExistente = localidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localidad no encontrada con id: " + id));

        localidadExistente.setDenominacion(localidad.getDenominacion());

        return localidadRepository.save(localidadExistente);
    }

    @Override
    public boolean deleteLocalidad(Long id) {

        Localidad localidad = localidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localidad no encontrada con id: " + id));

        localidadRepository.delete(localidad);
        return true;
    }
}