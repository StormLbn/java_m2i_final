package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {


}
