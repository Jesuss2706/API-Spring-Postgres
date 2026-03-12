package com.unicauca.spring_postgres_api.repository;

import com.unicauca.spring_postgres_api.modules.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
}
