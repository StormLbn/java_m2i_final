package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
    boolean existsByName(String name);
    Professional findByName(String name);
}
