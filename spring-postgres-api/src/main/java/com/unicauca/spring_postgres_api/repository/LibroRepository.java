package com.unicauca.spring_postgres_api.repository;

import com.unicauca.spring_postgres_api.modules.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
