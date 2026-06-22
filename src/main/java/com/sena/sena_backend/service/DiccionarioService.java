package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.CategoriaLsmResponse;
import com.sena.sena_backend.dto.PalabraLsmResponse;
import com.sena.sena_backend.model.CategoriaLsm;
import com.sena.sena_backend.model.PalabraLsm;
import com.sena.sena_backend.repository.CategoriaLsmRepository;
import com.sena.sena_backend.repository.PalabraLsmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiccionarioService {

    private final CategoriaLsmRepository categoriaRepository;
    private final PalabraLsmRepository palabraRepository;

    public DiccionarioService(
            CategoriaLsmRepository categoriaRepository,
            PalabraLsmRepository palabraRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.palabraRepository = palabraRepository;
    }

    public List<CategoriaLsmResponse> obtenerCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaLsmResponse::from)
                .toList();
    }

    public List<PalabraLsmResponse> obtenerPalabras() {
        return palabraRepository.findByActivaTrueOrderByPalabraAsc()
                .stream()
                .map(PalabraLsmResponse::from)
                .toList();
    }

    public List<PalabraLsmResponse> obtenerPorCategoria(Long categoriaId) {
        CategoriaLsm categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return palabraRepository.findByCategoriaAndActivaTrueOrderByPalabraAsc(categoria)
                .stream()
                .map(PalabraLsmResponse::from)
                .toList();
    }

    public List<PalabraLsmResponse> buscar(String texto) {
        return palabraRepository.findByPalabraContainingIgnoreCaseAndActivaTrueOrderByPalabraAsc(texto)
                .stream()
                .map(PalabraLsmResponse::from)
                .toList();
    }

    public PalabraLsmResponse obtenerDetalle(Long palabraId) {
        PalabraLsm palabra = palabraRepository.findById(palabraId)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada"));

        if (!palabra.getActiva()) {
            throw new RuntimeException("La palabra no está disponible");
        }

        return PalabraLsmResponse.from(palabra);
    }
}