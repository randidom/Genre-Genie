package com.techelevator.model;

import java.util.List;

public class Profile {


    private int profileId;
    private int userId;
    private String name;
    private String username;

    private String genreName;

    private List<Integer> genreIds;

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Profile(int profileId, List<Integer> genreIds) {
        this.profileId = profileId;
        this.genreIds = genreIds;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    //Default constructor
    public Profile() {
    }


    public Profile(int profileId, int userId, String name, String username) {
        this.profileId = profileId;
        this.userId = userId;
        this.name = name;
        this.username = username;
    }

    public Profile(int profileId, int userId, String name, String username, String genreName) {
        this.profileId = profileId;
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.genreName = genreName;
    }

    public Profile(int profileId, String name) {
        this.profileId = profileId;
        this.name = name;

    }

    public Profile(int profileId, int userId, String name) {
        this.profileId = profileId;
        this.userId = userId;
        this.name = name;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

