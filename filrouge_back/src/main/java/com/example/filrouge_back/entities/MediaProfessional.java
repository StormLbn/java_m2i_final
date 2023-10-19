package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.enums.JobForMedia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private JobForMedia job;

    // TODO définir cascade ?
    @ManyToOne()
    @JoinColumn(name = "media_id")
    @JsonIgnore
    private Media media;

    // TODO définir cascade ?
    @ManyToOne()
    @JoinColumn(name = "professional_id")
    private Professional professional;
}
