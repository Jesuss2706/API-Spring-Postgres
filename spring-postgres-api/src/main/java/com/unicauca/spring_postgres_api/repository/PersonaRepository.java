package com.unicauca.spring_postgres_api.repository;

import com.unicauca.spring_postgres_api.modules.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
