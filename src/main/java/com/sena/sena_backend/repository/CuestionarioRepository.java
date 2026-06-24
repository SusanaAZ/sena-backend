package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.Cuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuestionarioRepository extends JpaRepository<Cuestionario, Long> {
    List<Cuestionario> findByActivoTrueOrderByIdAsc();
    Optional<Cuestionario> findFirstByNivelIgnoreCaseAndActivoTrueOrderByIdAsc(String nivel);
}