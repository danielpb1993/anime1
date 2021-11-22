package com.example.anime1.controller;

import com.example.anime1.domain.model.Anime;
import com.example.anime1.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public List<Anime> findAllÇAnime() {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public Anime createMovie(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }
}