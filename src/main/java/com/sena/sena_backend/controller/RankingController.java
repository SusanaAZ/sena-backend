package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.RankingResponse;
import com.sena.sena_backend.service.RankingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public List<RankingResponse> obtenerRanking() {
        return rankingService.obtenerRanking();
    }
}