package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.HistorialResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.HistorialActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    private final HistorialActividadRepository historialRepository;

    public HistorialService(HistorialActividadRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public List<HistorialResponse> obtenerHistorial(Usuario usuario) {
        return historialRepository.findByUsuarioOrderByFechaDesc(usuario)
                .stream()
                .map(HistorialResponse::from)
                .toList();
    }
}