package com.unicauca.spring_postgres_api.repository;

import com.unicauca.spring_postgres_api.modules.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository <Autor, Long> {
}
