package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.enums.MediaType;
import org.springframework.data.domain.Page;
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

    Page<Media> findByGenres_GenreName(String genreName, Pageable pageable);
    Page<Media> findByType(MediaType type, Pageable pageable);
    Page<Media> findAllByOrderByIdDescReleaseYearDesc(Pageable pageable);
    Page<Media> findAllByOrderById(Pageable pageable);

    Page<Media> searchByTitleContainsIgnoreCase(String search, Pageable pageable);


    @Query("SELECT m FROM Media m JOIN m.genres g WHERE g IN :genres")
    Set<Media> findByGenresList(@Param("genres") List<Genre> genres);
}
