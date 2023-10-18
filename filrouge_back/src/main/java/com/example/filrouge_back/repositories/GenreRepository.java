package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    boolean existsByGenreName(String genreName);
    Genre findByGenreName(String genreName);
}
