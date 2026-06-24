package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.CuestionarioResponse;
import com.sena.sena_backend.dto.ResolverCuestionarioRequest;
import com.sena.sena_backend.dto.ResultadoCuestionarioResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.CuestionarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuestionarios")
public class CuestionarioController {

    private final CuestionarioService cuestionarioService;
    private final AuthService authService;

    public CuestionarioController(CuestionarioService cuestionarioService, AuthService authService) {
        this.cuestionarioService = cuestionarioService;
        this.authService = authService;
    }

    @GetMapping("/nivel/{nivel}")
    public CuestionarioResponse obtenerPorNivel(@PathVariable String nivel) {
        return cuestionarioService.obtenerPorNivel(nivel);
    }

    @PostMapping("/resolver")
    public ResultadoCuestionarioResponse resolver(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ResolverCuestionarioRequest request
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return cuestionarioService.resolver(usuario, request);
    }
}