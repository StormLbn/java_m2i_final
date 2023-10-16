package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.MediaProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaProfessionalRepository extends JpaRepository<MediaProfessional, UUID> {
}
