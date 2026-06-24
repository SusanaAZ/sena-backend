package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.*;
import com.sena.sena_backend.model.Cuestionario;
import com.sena.sena_backend.model.OpcionPregunta;
import com.sena.sena_backend.model.PreguntaCuestionario;
import com.sena.sena_backend.model.Usuario;
import com.sena.sena_backend.repository.CuestionarioRepository;
import com.sena.sena_backend.repository.OpcionPreguntaRepository;
import com.sena.sena_backend.repository.PreguntaCuestionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuestionarioService {

    private final CuestionarioRepository cuestionarioRepository;
    private final PreguntaCuestionarioRepository preguntaRepository;
    private final OpcionPreguntaRepository opcionRepository;
    private final ProgresoService progresoService;

    public CuestionarioService(
            CuestionarioRepository cuestionarioRepository,
            PreguntaCuestionarioRepository preguntaRepository,
            OpcionPreguntaRepository opcionRepository,
            ProgresoService progresoService
    ) {
        this.cuestionarioRepository = cuestionarioRepository;
        this.preguntaRepository = preguntaRepository;
        this.opcionRepository = opcionRepository;
        this.progresoService = progresoService;
    }

    public CuestionarioResponse obtenerPorNivel(String nivel) {
        Cuestionario cuestionario = cuestionarioRepository
                .findFirstByNivelIgnoreCaseAndActivoTrueOrderByIdAsc(nivel)
                .orElseThrow(() -> new RuntimeException("Cuestionario no encontrado"));

        return convertirACuestionarioResponse(cuestionario);
    }

    public ResultadoCuestionarioResponse resolver(Usuario usuario, ResolverCuestionarioRequest request) {
        Cuestionario cuestionario = cuestionarioRepository.findById(request.cuestionarioId())
                .orElseThrow(() -> new RuntimeException("Cuestionario no encontrado"));

        List<PreguntaCuestionario> preguntas = preguntaRepository
                .findByCuestionarioAndActivoTrueOrderByIdAsc(cuestionario);

        int correctas = 0;

        for (RespuestaPreguntaRequest respuesta : request.respuestas()) {
            OpcionPregunta opcion = opcionRepository.findById(respuesta.opcionId())
                    .orElseThrow(() -> new RuntimeException("Opción no encontrada"));

            if (opcion.getPregunta().getId().equals(respuesta.preguntaId()) && Boolean.TRUE.equals(opcion.getCorrecta())) {
                correctas++;
            }
        }

        int total = preguntas.size();
        int incorrectas = total - correctas;
        int puntosGanados = correctas * 10;

        progresoService.sumarPuntos(usuario, new SumarPuntosRequest(
                puntosGanados,
                "Cuestionario",
                cuestionario.getTitulo(),
                "Resolviste el cuestionario de nivel " + cuestionario.getNivel()
        ));

        return new ResultadoCuestionarioResponse(
                total,
                correctas,
                incorrectas,
                puntosGanados,
                "Cuestionario terminado"
        );
    }

    private CuestionarioResponse convertirACuestionarioResponse(Cuestionario cuestionario) {
        List<PreguntaCuestionarioResponse> preguntas = preguntaRepository
                .findByCuestionarioAndActivoTrueOrderByIdAsc(cuestionario)
                .stream()
                .map(pregunta -> new PreguntaCuestionarioResponse(
                        pregunta.getId(),
                        pregunta.getPregunta(),
                        pregunta.getImagenUrl(),
                        opcionRepository.findByPreguntaOrderByIdAsc(pregunta)
                                .stream()
                                .map(OpcionPreguntaResponse::from)
                                .toList()
                ))
                .toList();

        return new CuestionarioResponse(
                cuestionario.getId(),
                cuestionario.getTitulo(),
                cuestionario.getDescripcion(),
                cuestionario.getNivel(),
                preguntas
        );
    }
}