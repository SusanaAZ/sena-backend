package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.ProgresoResponse;
import com.sena.sena_backend.dto.SumarPuntosRequest;
import com.sena.sena_backend.model.HistorialActividad;
import com.sena.sena_backend.model.ProgresoUsuario;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.HistorialActividadRepository;
import com.sena.sena_backend.repository.ProgresoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ProgresoService {

    private final ProgresoUsuarioRepository progresoRepository;
    private final HistorialActividadRepository historialRepository;
    private final LogroService logroService;

    public ProgresoService(
            ProgresoUsuarioRepository progresoRepository,
            HistorialActividadRepository historialRepository,
            LogroService logroService
    ) {
        this.progresoRepository = progresoRepository;
        this.historialRepository = historialRepository;
        this.logroService = logroService;
    }

    public ProgresoResponse obtenerProgreso(Usuario usuario) {
        ProgresoUsuario progreso = obtenerOCrearProgreso(usuario);
        return ProgresoResponse.from(progreso);
    }

    public ProgresoResponse sumarPuntos(Usuario usuario, SumarPuntosRequest request) {
        ProgresoUsuario progreso = obtenerOCrearProgreso(usuario);

        int puntosGanados = request.puntos() == null ? 0 : request.puntos();

        actualizarRacha(progreso);

        progreso.setPuntos(progreso.getPuntos() + puntosGanados);
        progreso.setLeccionesCompletadas(progreso.getLeccionesCompletadas() + 1);
        progreso.setLeccionesHoy(progreso.getLeccionesHoy() + 1);
        progreso.setNivel(calcularNivel(progreso.getPuntos()));
        progreso.setFechaActualizacion(LocalDateTime.now());

        ProgresoUsuario guardado = progresoRepository.save(progreso);

        historialRepository.save(new HistorialActividad(
                usuario,
                request.tipo() == null ? "Actividad" : request.tipo(),
                request.titulo() == null ? "Actividad completada" : request.titulo(),
                request.descripcion() == null ? "Completaste una actividad en SENA." : request.descripcion(),
                puntosGanados
        ));

        logroService.revisarLogros(usuario);

        return ProgresoResponse.from(guardado);
    }

    private ProgresoUsuario obtenerOCrearProgreso(Usuario usuario) {
        return progresoRepository.findByUsuario(usuario)
                .orElseGet(() -> progresoRepository.save(new ProgresoUsuario(usuario)));
    }

    private void actualizarRacha(ProgresoUsuario progreso) {
        LocalDate hoy = LocalDate.now();
        LocalDate ultimaPractica = progreso.getUltimaPractica();

        if (ultimaPractica == null) {
            progreso.setRacha(1);
            progreso.setLeccionesHoy(0);
        } else if (ultimaPractica.equals(hoy)) {
            return;
        } else if (ultimaPractica.plusDays(1).equals(hoy)) {
            progreso.setRacha(progreso.getRacha() + 1);
            progreso.setLeccionesHoy(0);
        } else {
            progreso.setRacha(1);
            progreso.setLeccionesHoy(0);
        }

        progreso.setUltimaPractica(hoy);
    }

    private int calcularNivel(int puntos) {
        return (puntos / 100) + 1;
    }
}