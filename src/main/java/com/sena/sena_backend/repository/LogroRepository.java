package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.Logro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogroRepository extends JpaRepository<Logro, Long> {
    List<Logro> findByActivoTrueOrderByMetaAsc();
}