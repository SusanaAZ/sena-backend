package com.sena.sena_backend.dto;

import com.sena.sena_backend.model.Usuario;

public record UsuarioResponse(
        Long id,
        String nombre,
        String username,
        String correo,
        Integer avatar
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getCorreo(),
                usuario.getAvatar()
        );
    }
}