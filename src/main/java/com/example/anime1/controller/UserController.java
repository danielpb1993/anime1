package com.example.anime1.controller;

import com.example.anime1.domain.dto.ErrorMessage;
import com.example.anime1.domain.model.Favorite;
import com.example.anime1.domain.dto.ListResult;
import com.example.anime1.domain.dto.UserResult;
import com.example.anime1.domain.model.User;
import com.example.anime1.repository.FavoriteRepository;
import com.example.anime1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ResponseEntity<?> getAllUser() {
        List<UserResult> userList = userRepository.findAll()
                .stream()
                .map(UserResult::user)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(ListResult.list(userList));
    }

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody User newUser) {

        if (userRepository.findByUsername(newUser.username) == null) {
            User user = new User();
            user.username = newUser.username;
            user.password = passwordEncoder.encode(newUser.password);
            user.enabled = true;
            userRepository.save(user);
            UserResult userResponse = UserResult.user(user);
            return ResponseEntity.ok().body(userResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorMessage.message(String.format("Ja existeix un usuari amb el nom '%s'", newUser.username)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
            return ResponseEntity.ok()
                    .body(ErrorMessage.message( String.format("S'ha eliminat l'usuari amd id '%s'", id)));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.message(String.format("No s'ha trobat l'usuari amd id %s", id)));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllUser() {
        userRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/favorites")
    public ResponseEntity<?> addFavorite(@RequestBody Favorite favorite, Authentication authentication) {
        if (userRepository.findByUsername(authentication.getName()).userid == favorite.userid) {
            favoriteRepository.save(favorite);
            return ResponseEntity.ok().build();
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("Not authorized"));
    }
}