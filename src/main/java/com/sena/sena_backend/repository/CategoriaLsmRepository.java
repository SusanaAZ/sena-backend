package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.CategoriaLsm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaLsmRepository extends JpaRepository<CategoriaLsm, Long> {
    Optional<CategoriaLsm> findByNombreIgnoreCase(String nombre);
}