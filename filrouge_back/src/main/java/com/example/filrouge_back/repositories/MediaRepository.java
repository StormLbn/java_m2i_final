package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, UUID> {

    List<Media> findByGenres_GenreName(String genreName);

    List<Media> findByType(MediaType type);
}
