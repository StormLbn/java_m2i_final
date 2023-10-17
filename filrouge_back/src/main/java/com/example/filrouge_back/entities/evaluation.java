package com.example.filrouge_back.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID mediaId;

    @Column(nullable = false)
    private int rating;

    @Column(length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "mediaId", insertable = false, updatable = false)
    private Media media;

    public Evaluation(UUID userId, UUID mediaId, int rating, String comment) {
        this.userId = userId;
        this.mediaId = mediaId;
        this.rating = rating;
        this.comment = comment;
    }
}
