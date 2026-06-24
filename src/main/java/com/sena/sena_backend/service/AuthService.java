package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.LoginRequest;
import com.sena.sena_backend.dto.LoginResponse;
import com.sena.sena_backend.dto.RegistroRequest;
import com.sena.sena_backend.dto.UsuarioResponse;
import com.sena.sena_backend.model.PremiumUsuario;
import com.sena.sena_backend.model.ProgresoUsuario;
import com.sena.sena_backend.model.SesionUsuario;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.PremiumUsuarioRepository;
import com.sena.sena_backend.repository.ProgresoUsuarioRepository;
import com.sena.sena_backend.repository.SesionUsuarioRepository;
import com.sena.sena_backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final ProgresoUsuarioRepository progresoRepository;
    private final PremiumUsuarioRepository premiumRepository;
    private final SesionUsuarioRepository sesionRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            ProgresoUsuarioRepository progresoRepository,
            PremiumUsuarioRepository premiumRepository,
            SesionUsuarioRepository sesionRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.progresoRepository = progresoRepository;
        this.premiumRepository = premiumRepository;
        this.sesionRepository = sesionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByUsernameIgnoreCase(request.username())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (usuarioRepository.existsByCorreoIgnoreCase(request.correo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        String passwordHash = passwordEncoder.encode(request.password());

        Usuario usuario = new Usuario(
                request.nombre(),
                request.username(),
                request.correo(),
                passwordHash
        );

        Usuario guardado = usuarioRepository.save(usuario);

        progresoRepository.save(new ProgresoUsuario(guardado));
        premiumRepository.save(new PremiumUsuario(guardado));

        String token = UUID.randomUUID().toString();
        sesionRepository.save(new SesionUsuario(token, guardado));

        return new LoginResponse(token, UsuarioResponse.from(guardado));
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.password(), usuario.getPasswordHash())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        String token = UUID.randomUUID().toString();
        sesionRepository.save(new SesionUsuario(token, usuario));

        return new LoginResponse(token, UsuarioResponse.from(usuario));
    }

    public Usuario obtenerUsuarioPorToken(String authorizationHeader) {
        String token = limpiarToken(authorizationHeader);

        return sesionRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Sesión inválida"))
                .getUsuario();
    }

    public void logout(String authorizationHeader) {
        String token = limpiarToken(authorizationHeader);
        sesionRepository.deleteByToken(token);
    }

    private String limpiarToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no enviado");
        }

        return authorizationHeader.replace("Bearer ", "").trim();
    }
}