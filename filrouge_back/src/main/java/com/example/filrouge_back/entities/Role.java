package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;


    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserEntity> userEntityList;


}
