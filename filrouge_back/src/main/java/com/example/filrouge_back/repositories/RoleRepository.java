package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Role;
import com.example.filrouge_back.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean existsByRoleName(RoleName roleName);

    Optional<Role> findByRoleName(RoleName roleName);
}
