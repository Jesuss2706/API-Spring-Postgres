package com.unicauca.spring_postgres_api.repository;

import com.unicauca.spring_postgres_api.modules.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
