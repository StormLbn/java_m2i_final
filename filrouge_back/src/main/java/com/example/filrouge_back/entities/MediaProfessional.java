package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.JobForMedia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
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
