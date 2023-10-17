package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {

    List<Media> findByGenres_GenreName(String genreName);

}
