package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.UsuarioResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final AuthService authService;

    public UsuarioController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/me")
    public UsuarioResponse obtenerMiPerfil(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return UsuarioResponse.from(usuario);
    }
}