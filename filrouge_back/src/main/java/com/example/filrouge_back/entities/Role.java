package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
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
