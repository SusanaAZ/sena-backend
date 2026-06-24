package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.SesionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SesionUsuarioRepository extends JpaRepository<SesionUsuario, Long> {
    Optional<SesionUsuario> findByToken(String token);
    void deleteByToken(String token);
}