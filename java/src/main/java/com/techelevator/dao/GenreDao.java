package com.techelevator.dao;

import com.techelevator.model.Genre;

import java.security.Principal;
import java.util.List;

public interface GenreDao {
    List<Genre> getAllGenres();
    //This lists all of the genre id's associated with the genre name

    Genre getGenreById(int id);
    //This grabs the genre name associated with it's particular ID

    Genre getGenrePreferences(int id);
    //This grabs the preferences tied to the user_id

    void addGenresToUser(Genre genres, Principal principal);
    //When a user registers, they are asked to select up to three genre preferences, this will grab the
    //genre id's from those and add it to the profile_genre table since many profiles can have many id's.
}
