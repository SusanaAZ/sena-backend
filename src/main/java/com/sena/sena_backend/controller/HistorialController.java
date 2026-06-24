package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.HistorialResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.HistorialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final AuthService authService;
    private final HistorialService historialService;

    public HistorialController(AuthService authService, HistorialService historialService) {
        this.authService = authService;
        this.historialService = historialService;
    }

    @GetMapping("/me")
    public List<HistorialResponse> obtenerMiHistorial(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return historialService.obtenerHistorial(usuario);
    }
}