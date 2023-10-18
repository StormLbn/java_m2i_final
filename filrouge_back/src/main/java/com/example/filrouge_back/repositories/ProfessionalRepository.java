package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
    boolean existsByName(String name);
    Professional findByName(String name);
}
