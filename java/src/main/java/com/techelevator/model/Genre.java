package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Genre {
    @JsonProperty("genre_ids")
    private int genreId;

    @JsonProperty("name")
    private String genreName;

    private List<Integer> genreIds = new ArrayList<>();

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    private int userId;
    //Constructor without ID since it will serialize with need genre's implemented
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(int  genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public Genre(int genreId, int userId) {
        this.genreId = genreId;
        this.userId = userId;
    }

    //Default constructor
    public Genre() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
