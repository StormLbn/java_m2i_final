package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.JobForMedia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MediaProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private JobForMedia job;

    // TODO définir cascade ?
    @ManyToOne()
    @JoinColumn(name = "media_id")
    private Media media;

    // TODO définir cascade ?
    @ManyToOne()
    @JoinColumn(name = "professional_id")
    private Professional professional;
}
