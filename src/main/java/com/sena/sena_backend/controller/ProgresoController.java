package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.ProgresoResponse;
import com.sena.sena_backend.dto.SumarPuntosRequest;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.ProgresoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progreso")
public class ProgresoController {

    private final AuthService authService;
    private final ProgresoService progresoService;

    public ProgresoController(AuthService authService, ProgresoService progresoService) {
        this.authService = authService;
        this.progresoService = progresoService;
    }

    @GetMapping("/me")
    public ProgresoResponse obtenerMiProgreso(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return progresoService.obtenerProgreso(usuario);
    }

    @PostMapping("/sumar-puntos")
    public ProgresoResponse sumarPuntos(
            @RequestHeader("Authorization") String authorization,
            @RequestBody SumarPuntosRequest request
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return progresoService.sumarPuntos(usuario, request);
    }
}