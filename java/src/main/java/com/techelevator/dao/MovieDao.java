package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAllMovies();
    Movie getMoviesByGenre(String genre);
    Movie findMovieByTitle(String title);
    Movie findMoviesByActors(String actors);
    Movie findMoviesByReleaseYear(int year);
    Movie findMoviesByDirector(String director);
    Movie addMovie(Movie movie);
    //Admin users can only add movies
    Movie getFavoriteMovies(Movie movie);
    //Grabs only the users favorite movies that the selected

}
