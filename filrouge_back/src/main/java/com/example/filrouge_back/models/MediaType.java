package com.example.filrouge_back.models;

public enum MediaType {
    MOVIE("film"),
    SHOW("série");

    final String valueFr;

    MediaType(String valueFr) {
        this.valueFr = valueFr;
    }

}
