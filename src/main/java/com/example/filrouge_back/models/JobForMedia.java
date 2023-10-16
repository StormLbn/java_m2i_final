package com.example.filrouge_back.models;

public enum JobForMedia {
    ACTOR("acteur"),
    DIRECTOR("directeur"),
    WRITER("scénariste"),
    PRODUCER("producteur");

    final String valueFr;

    JobForMedia(String valueFr) {
        this.valueFr = valueFr;
    }

}
