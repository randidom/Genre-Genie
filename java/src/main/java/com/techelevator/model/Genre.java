package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
    @JsonProperty("genre_id")
    private int genreId;

    @JsonProperty("name")
    private String genreName;

    //Constructor without ID since it will serialize with need genre's implemented
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    //Default constructor
    public Genre() {
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
