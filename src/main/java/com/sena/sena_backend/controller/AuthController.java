package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.LoginRequest;
import com.sena.sena_backend.dto.LoginResponse;
import com.sena.sena_backend.dto.MensajeResponse;
import com.sena.sena_backend.dto.RegistroRequest;
import com.sena.sena_backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public LoginResponse registrar(@RequestBody RegistroRequest request) {
        return authService.registrar(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public MensajeResponse logout(@RequestHeader("Authorization") String authorization) {
        authService.logout(authorization);
        return new MensajeResponse("Sesión cerrada correctamente");
    }
}