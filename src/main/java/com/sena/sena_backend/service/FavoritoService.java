package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.FavoritoResponse;
import com.sena.sena_backend.dto.MensajeResponse;
import com.sena.sena_backend.model.FavoritoPalabra;
import com.sena.sena_backend.model.PalabraLsm;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.FavoritoPalabraRepository;
import com.sena.sena_backend.repository.PalabraLsmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoPalabraRepository favoritoRepository;
    private final PalabraLsmRepository palabraRepository;

    public FavoritoService(
            FavoritoPalabraRepository favoritoRepository,
            PalabraLsmRepository palabraRepository
    ) {
        this.favoritoRepository = favoritoRepository;
        this.palabraRepository = palabraRepository;
    }

    public List<FavoritoResponse> obtenerMisFavoritos(Usuario usuario) {
        return favoritoRepository.findByUsuarioOrderByFechaGuardadoDesc(usuario)
                .stream()
                .map(FavoritoResponse::from)
                .toList();
    }

    public MensajeResponse agregarFavorito(Usuario usuario, Long palabraId) {
        PalabraLsm palabra = palabraRepository.findById(palabraId)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada"));

        if (!Boolean.TRUE.equals(palabra.getActiva())) {
            throw new RuntimeException("La palabra no está disponible");
        }

        if (favoritoRepository.existsByUsuarioAndPalabra(usuario, palabra)) {
            return new MensajeResponse("La palabra ya estaba en favoritos");
        }

        favoritoRepository.save(new FavoritoPalabra(usuario, palabra));

        return new MensajeResponse("Palabra agregada a favoritos");
    }

    @Transactional
    public MensajeResponse eliminarFavorito(Usuario usuario, Long palabraId) {
        PalabraLsm palabra = palabraRepository.findById(palabraId)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada"));

        favoritoRepository.deleteByUsuarioAndPalabra(usuario, palabra);

        return new MensajeResponse("Palabra eliminada de favoritos");
    }

    public boolean esFavorito(Usuario usuario, Long palabraId) {
        PalabraLsm palabra = palabraRepository.findById(palabraId)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada"));

        return favoritoRepository.existsByUsuarioAndPalabra(usuario, palabra);
    }
}