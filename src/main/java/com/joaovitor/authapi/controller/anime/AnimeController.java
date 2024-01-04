package com.joaovitor.authapi.controller.anime;

import com.joaovitor.authapi.domain.anime.Anime;
import com.joaovitor.authapi.dto.anime.AnimeRequestDTO;
import com.joaovitor.authapi.dto.anime.AnimeResponseDTO;
import com.joaovitor.authapi.service.anime.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/animes")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<List<AnimeResponseDTO>> list() {
        List<AnimeResponseDTO> animes = animeService.findAll().stream().map(AnimeResponseDTO::fromEntity).toList();
        return ResponseEntity.ok(animes);
    }

    @PostMapping
    public ResponseEntity<AnimeResponseDTO> create(@RequestBody AnimeRequestDTO animeRequestDTO) {
        Anime anime = animeService.save(animeRequestDTO.toEntity());
        AnimeResponseDTO animeResponseDTO = AnimeResponseDTO.fromEntity(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(animeResponseDTO);
    }
}
