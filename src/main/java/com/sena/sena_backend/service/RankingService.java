package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.RankingResponse;
import com.sena.sena_backend.repository.ProgresoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    private final ProgresoUsuarioRepository progresoRepository;

    public RankingService(ProgresoUsuarioRepository progresoRepository) {
        this.progresoRepository = progresoRepository;
    }

    public List<RankingResponse> obtenerRanking() {
        return progresoRepository.findTop20ByOrderByPuntosDesc()
                .stream()
                .map(progreso -> new RankingResponse(
                        progreso.getUsuario().getId(),
                        progreso.getUsuario().getNombre(),
                        progreso.getUsuario().getUsername(),
                        progreso.getUsuario().getAvatar(),
                        progreso.getPuntos(),
                        progreso.getNivel()
                ))
                .toList();
    }
}