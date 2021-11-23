package com.example.anime1.controller;

import com.example.anime1.domain.dto.ErrorMessage;
import com.example.anime1.domain.dto.ListResult;
import com.example.anime1.domain.model.Anime;
import com.example.anime1.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAnime() {
        List<Anime> animeList = animeRepository.findAll();
        return ResponseEntity.ok().body(ListResult.list(animeList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimeId(@PathVariable UUID id) {
        Anime anime = animeRepository.findById(id).orElse(null);
        if (anime == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessage.message("No s'ha trobat l'anime amd id \'" + id + "\'"));
        }

      return ResponseEntity.ok().body(anime);
    }

    @PostMapping("/")
    public ResponseEntity<?> addAnime(@RequestBody Anime anime) {
        if (animeRepository.findByname(anime.name) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ErrorMessage.message("Ja existeix un anime amb el nom \'" + anime.name + "\'"));
        }
        return ResponseEntity.ok().body(animeRepository.save(anime));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteAnime()
}