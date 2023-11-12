package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.enums.MediaType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, UUID> {

    List<Media> findByGenres_GenreName(String genreName, Pageable pageable);
    List<Media> findByType(MediaType type, Pageable pageable);
    List<Media> findAllByOrderByIdDescReleaseYearDesc(Pageable pageable);

    List<Media> searchByTitleContainsIgnoreCase(String search);

    @Query("SELECT m FROM Media m JOIN m.genres g WHERE g IN :genres")
    Set<Media> findByGenresList(@Param("genres") List<Genre> genres);
}
