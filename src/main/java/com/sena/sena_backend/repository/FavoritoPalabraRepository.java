package com.sena.sena_backend.repository;

import com.sena.sena_backend.model.FavoritoPalabra;
import com.sena.sena_backend.model.PalabraLsm;
import com.sena.sena_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoPalabraRepository extends JpaRepository<FavoritoPalabra, Long> {

    List<FavoritoPalabra> findByUsuarioOrderByFechaGuardadoDesc(Usuario usuario);

    Optional<FavoritoPalabra> findByUsuarioAndPalabra(Usuario usuario, PalabraLsm palabra);

    boolean existsByUsuarioAndPalabra(Usuario usuario, PalabraLsm palabra);

    void deleteByUsuarioAndPalabra(Usuario usuario, PalabraLsm palabra);
}