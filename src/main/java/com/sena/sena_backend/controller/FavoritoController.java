package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.FavoritoResponse;
import com.sena.sena_backend.dto.MensajeResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.FavoritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final AuthService authService;
    private final FavoritoService favoritoService;

    public FavoritoController(AuthService authService, FavoritoService favoritoService) {
        this.authService = authService;
        this.favoritoService = favoritoService;
    }

    @GetMapping("/me")
    public List<FavoritoResponse> obtenerMisFavoritos(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return favoritoService.obtenerMisFavoritos(usuario);
    }

    @PostMapping("/{palabraId}")
    public MensajeResponse agregarFavorito(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long palabraId
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return favoritoService.agregarFavorito(usuario, palabraId);
    }

    @DeleteMapping("/{palabraId}")
    public MensajeResponse eliminarFavorito(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long palabraId
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return favoritoService.eliminarFavorito(usuario, palabraId);
    }

    @GetMapping("/{palabraId}/estado")
    public Map<String, Boolean> verificarFavorito(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long palabraId
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return Map.of("favorito", favoritoService.esFavorito(usuario, palabraId));
    }
}