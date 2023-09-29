package com.techelevator.dao;

import com.techelevator.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAllGenres();
    //This lists all of the genre id's associated with the genre name

    Genre getGenreById(int id);
    //This grabs the genre name associated with it's particular ID
}
