package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.PremiumUsuario;
import com.sena.sena_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PremiumUsuarioRepository extends JpaRepository<PremiumUsuario, Long> {

    Optional<PremiumUsuario> findByUsuario(Usuario usuario);
}