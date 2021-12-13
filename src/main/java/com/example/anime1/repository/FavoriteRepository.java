package com.example.anime1.repository;

import com.example.anime1.domain.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {

}
