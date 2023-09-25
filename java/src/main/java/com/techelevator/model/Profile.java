package com.techelevator.model;

public class Profile {

    private int id;
    private String name;
    private int profileGenreId;

    //Default constructor
    public Profile() {
    }

    public Profile(String name, int profileGenreId) {
        this.name = name;
        this.profileGenreId = profileGenreId;
    }

    public Profile(int id, String name, int profileGenreId) {
        this.id = id;
        this.name = name;
        this.profileGenreId = profileGenreId;
    }

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

    public int getProfileGenreId() {
        return profileGenreId;
    }

    public void setProfileGenreId(int profileGenreId) {
        this.profileGenreId = profileGenreId;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profileGenreID=" + profileGenreId +
                '}';
    }
}
