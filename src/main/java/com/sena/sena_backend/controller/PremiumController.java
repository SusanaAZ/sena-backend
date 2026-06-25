package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.PremiumRequest;
import com.sena.sena_backend.dto.PremiumResponse;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.service.AuthService;
import com.sena.sena_backend.service.PremiumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private final PremiumService premiumService;
    private final AuthService authService;

    public PremiumController(PremiumService premiumService, AuthService authService) {
        this.premiumService = premiumService;
        this.authService = authService;
    }

    @GetMapping("/me")
    public PremiumResponse obtenerMiPremium(@RequestHeader("Authorization") String authorization) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        return premiumService.obtenerPremium(usuario);
    }

    @PostMapping("/activar")
    public PremiumResponse activarPremium(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody(required = false) PremiumRequest request
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);

        if (request == null) {
            return premiumService.activarPremium(usuario);
        }

        return premiumService.activarPremium(usuario, request);
    }

    @GetMapping("/estado")
    public ResponseEntity<PremiumResponse> estadoPremium(
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        Usuario usuario = authService.obtenerUsuarioPorToken(authorization);
        PremiumResponse response = premiumService.obtenerEstadoPremium(usuario);
        return ResponseEntity.ok(response);
    }
}