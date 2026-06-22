package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.LogroResponse;
import com.sena.sena_backend.dto.UsuarioLogroResponse;
import com.sena.sena_backend.model.Logro;
import com.sena.sena_backend.model.ProgresoUsuario;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.model.UsuarioLogro;
import com.sena.sena_backend.repository.LogroRepository;
import com.sena.sena_backend.repository.ProgresoUsuarioRepository;
import com.sena.sena_backend.repository.UsuarioLogroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogroService {

    private final LogroRepository logroRepository;
    private final UsuarioLogroRepository usuarioLogroRepository;
    private final ProgresoUsuarioRepository progresoRepository;

    public LogroService(
            LogroRepository logroRepository,
            UsuarioLogroRepository usuarioLogroRepository,
            ProgresoUsuarioRepository progresoRepository
    ) {
        this.logroRepository = logroRepository;
        this.usuarioLogroRepository = usuarioLogroRepository;
        this.progresoRepository = progresoRepository;
    }

    public List<LogroResponse> obtenerTodos() {
        return logroRepository.findByActivoTrueOrderByMetaAsc()
                .stream()
                .map(LogroResponse::from)
                .toList();
    }

    public List<UsuarioLogroResponse> obtenerMisLogros(Usuario usuario) {
        return usuarioLogroRepository.findByUsuarioOrderByFechaDesbloqueoDesc(usuario)
                .stream()
                .map(UsuarioLogroResponse::from)
                .toList();
    }

    public void revisarLogros(Usuario usuario) {
        ProgresoUsuario progreso = progresoRepository.findByUsuario(usuario)
                .orElse(null);

        if (progreso == null) {
            return;
        }

        List<Logro> logros = logroRepository.findByActivoTrueOrderByMetaAsc();

        for (Logro logro : logros) {
            if (usuarioLogroRepository.existsByUsuarioAndLogro(usuario, logro)) {
                continue;
            }

            if (cumpleLogro(progreso, logro)) {
                usuarioLogroRepository.save(new UsuarioLogro(usuario, logro));
            }
        }
    }

    private boolean cumpleLogro(ProgresoUsuario progreso, Logro logro) {
        String tipo = logro.getTipo();
        Integer meta = logro.getMeta();

        if (tipo == null || meta == null) {
            return false;
        }

        return switch (tipo.toUpperCase()) {
            case "PUNTOS" -> progreso.getPuntos() >= meta;
            case "NIVEL" -> progreso.getNivel() >= meta;
            case "RACHA" -> progreso.getRacha() >= meta;
            case "LECCIONES" -> progreso.getLeccionesCompletadas() >= meta;
            default -> false;
        };
    }
}