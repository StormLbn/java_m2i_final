package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Professional;
import com.example.filrouge_back.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

}
