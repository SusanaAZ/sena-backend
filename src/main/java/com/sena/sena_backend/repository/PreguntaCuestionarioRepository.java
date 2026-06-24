package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.Cuestionario;
import com.sena.sena_backend.model.PreguntaCuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntaCuestionarioRepository extends JpaRepository<PreguntaCuestionario, Long> {
    List<PreguntaCuestionario> findByCuestionarioAndActivoTrueOrderByIdAsc(Cuestionario cuestionario);
}