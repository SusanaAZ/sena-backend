package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.PremiumResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.PremiumService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private final AuthService authService;
    private final PremiumService premiumService;

    public PremiumController(AuthService authService, PremiumService premiumService) {
        this.authService = authService;
        this.premiumService = premiumService;
    }

    @GetMapping("/me")
    public PremiumResponse obtenerMiPremium(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return premiumService.obtenerPremium(usuario);
    }

    @PostMapping("/activar")
    public PremiumResponse activarPremium(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return premiumService.activarPremium(usuario);
    }
}