package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.model.Movie;

import java.util.List;

public class MovieController implements MovieDao {
    @Override
    public List<Movie> findAllMovies() {
        return null;
    }

    @Override
    public Movie getMoviesByGenre(String genre) {
        return null;
    }

    @Override
    public Movie findMovieByTitle(String title) {
        return null;
    }

    @Override
    public Movie findMoviesByActors(String actors) {
        return null;
    }

    @Override
    public Movie findMoviesByReleaseYear(int year) {
        return null;
    }

    @Override
    public Movie findMoviesByDirector(String director) {
        return null;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public Movie getFavoriteMovies(Movie movie) {
        return null;
    }
}
