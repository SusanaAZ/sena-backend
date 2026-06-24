package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.ProgresoUsuario;
import com.sena.sena_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgresoUsuarioRepository extends JpaRepository<ProgresoUsuario, Long> {

    Optional<ProgresoUsuario> findByUsuario(Usuario usuario);

    List<ProgresoUsuario> findTop20ByOrderByPuntosDesc();
}