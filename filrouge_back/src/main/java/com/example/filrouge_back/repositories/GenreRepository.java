package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    boolean existsByGenreName(String genreName);
    Genre findByGenreName(String genreName);
}
