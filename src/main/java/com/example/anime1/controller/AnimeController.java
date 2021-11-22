package com.example.anime1.controller;

import com.example.anime1.domain.model.Anime;
import com.example.anime1.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public List<Anime> findAllAnime() {
        return animeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Anime getAnimeId(@PathVariable UUID id) {
        return animeRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Anime createMovie(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }
}