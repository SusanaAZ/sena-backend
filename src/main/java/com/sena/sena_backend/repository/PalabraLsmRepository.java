package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.CategoriaLsm;
import com.sena.sena_backend.model.PalabraLsm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalabraLsmRepository extends JpaRepository<PalabraLsm, Long> {

    List<PalabraLsm> findByActivaTrueOrderByPalabraAsc();

    List<PalabraLsm> findByCategoriaAndActivaTrueOrderByPalabraAsc(CategoriaLsm categoria);

    List<PalabraLsm> findByPalabraContainingIgnoreCaseAndActivaTrueOrderByPalabraAsc(String palabra);
}