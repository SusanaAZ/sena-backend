package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.PremiumResponse;
import com.sena.sena_backend.model.PremiumUsuario;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.PremiumUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PremiumService {

    private final PremiumUsuarioRepository premiumRepository;

    public PremiumService(PremiumUsuarioRepository premiumRepository) {
        this.premiumRepository = premiumRepository;
    }

    public PremiumResponse obtenerPremium(Usuario usuario) {
        PremiumUsuario premium = obtenerOCrearPremium(usuario);
        return PremiumResponse.from(premium);
    }

    public PremiumResponse activarPremium(Usuario usuario) {
        PremiumUsuario premium = obtenerOCrearPremium(usuario);

        premium.setActivo(true);
        premium.setTipoPlan("PREMIUM");
        premium.setFechaActivacion(LocalDateTime.now());

        return PremiumResponse.from(premiumRepository.save(premium));
    }

    private PremiumUsuario obtenerOCrearPremium(Usuario usuario) {
        return premiumRepository.findByUsuario(usuario)
                .orElseGet(() -> premiumRepository.save(new PremiumUsuario(usuario)));
    }
}