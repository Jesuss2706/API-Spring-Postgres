package com.unicauca.spring_postgres_api.services;

import com.unicauca.spring_postgres_api.modules.Domicilio;
import com.unicauca.spring_postgres_api.repository.DomicilioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioServiceImpl implements DomicilioService {

    private final DomicilioRepository domicilioRepository;

    public DomicilioServiceImpl(DomicilioRepository domicilioRepository){
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public List<Domicilio> getDomicilios() {
        return domicilioRepository.findAll();
    }

    @Override
    public Domicilio getDomicilio(Long id) {
        return domicilioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado con id: " + id));
    }

    @Override
    public Domicilio createDomicilio(Domicilio domicilio) {

        if (domicilio.getCalle() == null || domicilio.getCalle().isEmpty()) {
            throw new RuntimeException("La calle del domicilio es obligatoria");
        }

        return domicilioRepository.save(domicilio);
    }

    @Override
    public Domicilio updateDomicilio(Long id, Domicilio domicilio) {

        Domicilio domicilioExistente = domicilioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado con id: " + id));

        domicilioExistente.setCalle(domicilio.getCalle());
        domicilioExistente.setNumero(domicilio.getNumero());
        domicilioExistente.setLocalidad(domicilio.getLocalidad());

        return domicilioRepository.save(domicilioExistente);
    }

    @Override
    public boolean deleteDomicilio(long id) {

        Domicilio domicilio = domicilioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado con id: " + id));

        domicilioRepository.delete(domicilio);
        return true;
    }
}