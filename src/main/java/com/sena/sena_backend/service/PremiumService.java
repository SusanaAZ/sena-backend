package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.PremiumRequest;
import com.sena.sena_backend.dto.PremiumResponse;
import com.sena.sena_backend.model.PremiumUsuario;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.PremiumUsuarioRepository;
import com.sena.sena_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class PremiumService {

    private final PremiumUsuarioRepository premiumRepository;
    private final UsuarioRepository usuarioRepository;

    public PremiumService(PremiumUsuarioRepository premiumRepository, UsuarioRepository usuarioRepository) {
        this.premiumRepository = premiumRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public PremiumResponse obtenerPremium(Usuario usuario) {
        PremiumUsuario premium = obtenerOCrearPremium(usuario);
        return PremiumResponse.from(premium);
    }

    @Transactional
    public PremiumResponse activarPremium(Usuario usuario) {
        PremiumUsuario premium = activarRegistroPremium(usuario);
        return PremiumResponse.from(premium);
    }

    @Transactional
    public PremiumResponse activarPremium(Usuario usuario, PremiumRequest request) {
        validarRequest(request);
        activarRegistroPremium(usuario);
        return new PremiumResponse(true, "Premium activado correctamente");
    }

    @Transactional(readOnly = true)
    public PremiumResponse obtenerEstadoPremium(Usuario usuario) {
        if (usuario == null) {
            throw new RuntimeException("Sesión inválida");
        }

        PremiumUsuario premium = obtenerOCrearPremium(usuario);
        boolean activo = usuario.isPremium() || premium.isActivo();

        return new PremiumResponse(
                activo,
                activo ? "Cuenta Premium" : "Cuenta Gratis"
        );
    }

    private PremiumUsuario activarRegistroPremium(Usuario usuario) {
        if (usuario == null) {
            throw new RuntimeException("Sesión inválida");
        }

        PremiumUsuario premium = obtenerOCrearPremium(usuario);
        premium.setActivo(true);
        premium.setTipoPlan("PREMIUM");
        premium.setFechaActivacion(LocalDateTime.now());

        usuario.setPremium(true);
        usuarioRepository.save(usuario);

        return premiumRepository.save(premium);
    }

    private PremiumUsuario obtenerOCrearPremium(Usuario usuario) {
        if (usuario == null) {
            throw new RuntimeException("Sesión inválida");
        }

        return premiumRepository.findByUsuario(usuario)
                .orElseGet(() -> premiumRepository.save(new PremiumUsuario(usuario)));
    }

    private void validarRequest(PremiumRequest request) {
        if (request == null) {
            throw new RuntimeException("Datos de pago incompletos");
        }

        if (!StringUtils.hasText(request.getNombreTitular())) {
            throw new RuntimeException("Ingresa el nombre del titular");
        }

        if (!StringUtils.hasText(request.getUltimos4()) || request.getUltimos4().length() != 4) {
            throw new RuntimeException("Datos de tarjeta inválidos");
        }

        if (!StringUtils.hasText(request.getMarcaTarjeta())) {
            throw new RuntimeException("Marca de tarjeta inválida");
        }
    }
}