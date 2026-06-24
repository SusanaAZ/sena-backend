package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.Logro;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.model.UsuarioLogro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioLogroRepository extends JpaRepository<UsuarioLogro, Long> {

    List<UsuarioLogro> findByUsuarioOrderByFechaDesbloqueoDesc(Usuario usuario);

    boolean existsByUsuarioAndLogro(Usuario usuario, Logro logro);
}