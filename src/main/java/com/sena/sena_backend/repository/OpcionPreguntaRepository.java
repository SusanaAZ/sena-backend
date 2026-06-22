package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.OpcionPregunta;
import com.sena.sena_backend.model.PreguntaCuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpcionPreguntaRepository extends JpaRepository<OpcionPregunta, Long> {
    List<OpcionPregunta> findByPreguntaOrderByIdAsc(PreguntaCuestionario pregunta);
}