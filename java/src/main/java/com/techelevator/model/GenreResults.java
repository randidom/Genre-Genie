package com.techelevator.model;

public class GenreResults {

    //This is the JSON format from the external API for the list of genres
    //This class is referred and use to reference the HTTP.get request in the Genre controller
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
