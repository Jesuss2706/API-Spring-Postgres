package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Domicilio;

import java.util.List;

public interface DomicilioService {

    List<Domicilio> getDomicilios();
    Domicilio getDomicilio(Long id);
    Domicilio createDomicilio(Domicilio domicilio);
    Domicilio updateDomicilio(Long id, Domicilio domicilio);
    boolean deleteDomicilio(long id);
}
