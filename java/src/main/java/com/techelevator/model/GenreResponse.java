package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GenreResponse {

    //These are JSON properties from the external API, the genre is set as an array of genres with their own unique id and name
    //The names and id's correspond to the genre results class
    @JsonProperty("genres")
    private List<GenreResults> allGenres = new ArrayList<>();

    public List<GenreResults> getAllGenres() {
        return allGenres;
    }

    public void setAllGenres(List<GenreResults> allGenres) {
        this.allGenres = allGenres;
    }
}
