package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieDao {
    List<Movie> findAllMovies();
    //This calls the external API and lists all the movies found in that external API
    List<Movie> getMoviesByGenre(int genre);
    //This specific method connects the genre id with the associated movies in the external API
    Movie findMovieByTitle(String title);
    //This allows users to search by title for the movie

    Movie getMovieById(int id);
    //This method searches by the movie id, specifically

    List<Movie> getFavoriteMovies(int userId);
    //Grabs only the users favorite movies that the selected, specific by the userId
    Movie createFavorite(Movie movie);
    //If the user selects a movie to favorite, this will create the movie into our own database and set the favorite status to true
    void deleteFavorite(int userId, int movieId);

}
