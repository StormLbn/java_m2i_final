package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.RoleList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING) // Spécifiez le type de l'enum (STRING ou ORDINAL)
    private RoleName roleName;


    @OneToMany(mappedBy = "role")
    private List<UserEntity> userEntityList;


}
