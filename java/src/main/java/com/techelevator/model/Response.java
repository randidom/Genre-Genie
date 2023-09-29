package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Response {

    //These are JSON properties from the external API, the movies come in pages, along with an array of results
    @JsonProperty("results")
    private List<Results> allMovies = new ArrayList<>();
    @JsonProperty("page")
    private int page;

    public int getPages() {
        return page;
    }

    public void setPages(int page) {
        this.page = page;
    }

    public List<Results> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(List<Results> allMovies) {
        this.allMovies = allMovies;
    }
}
