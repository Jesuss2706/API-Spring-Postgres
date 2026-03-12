package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Localidad;

import java.util.List;

public interface LocalidadService {

    List<Localidad> getLocalidades();
    Localidad getLocalidad(Long id);
    Localidad createLocalidad(Localidad localidad);
    Localidad updateLocalidad(Long id, Localidad localidad);
    boolean deleteLocalidad(Long id);
}
