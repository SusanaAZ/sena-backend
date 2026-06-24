package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.CategoriaLsmResponse;
import com.sena.sena_backend.dto.PalabraLsmResponse;
import com.sena.sena_backend.service.DiccionarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diccionario")
public class DiccionarioController {

    private final DiccionarioService diccionarioService;

    public DiccionarioController(DiccionarioService diccionarioService) {
        this.diccionarioService = diccionarioService;
    }

    @GetMapping("/categorias")
    public List<CategoriaLsmResponse> obtenerCategorias() {
        return diccionarioService.obtenerCategorias();
    }

    @GetMapping("/palabras")
    public List<PalabraLsmResponse> obtenerPalabras() {
        return diccionarioService.obtenerPalabras();
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<PalabraLsmResponse> obtenerPorCategoria(@PathVariable Long categoriaId) {
        return diccionarioService.obtenerPorCategoria(categoriaId);
    }

    @GetMapping("/buscar")
    public List<PalabraLsmResponse> buscar(@RequestParam String texto) {
        return diccionarioService.buscar(texto);
    }

    @GetMapping("/palabras/{palabraId}")
    public PalabraLsmResponse obtenerDetalle(@PathVariable Long palabraId) {
        return diccionarioService.obtenerDetalle(palabraId);
    }
}