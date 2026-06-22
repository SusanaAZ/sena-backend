package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.LogroResponse;
import com.sena.sena_backend.dto.UsuarioLogroResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.LogroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logros")
public class LogroController {

    private final AuthService authService;
    private final LogroService logroService;

    public LogroController(AuthService authService, LogroService logroService) {
        this.authService = authService;
        this.logroService = logroService;
    }

    @GetMapping
    public List<LogroResponse> obtenerTodos() {
        return logroService.obtenerTodos();
    }

    @GetMapping("/me")
    public List<UsuarioLogroResponse> obtenerMisLogros(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return logroService.obtenerMisLogros(usuario);
    }
}