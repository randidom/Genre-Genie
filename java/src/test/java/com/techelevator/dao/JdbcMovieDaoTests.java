package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import com.techelevator.model.Profile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JdbcMovieDaoTests extends BaseDaoTests{




    // Set as an array because a movie can have multiple genres and this is how it is inputted in the external API
    private final Movie MOVIE_1 = new Movie(200, "Barbie", "2023-08-23","Barbie wants to become human", 9.5, false, 2);
    private final Movie MOVIE_2 = new Movie(201, "Spider-man","2022-05-09", "Tom Holland takes the role of Spider-man", 9.8, true, 3);
    private final Movie MOVIE_3 = new Movie(202, "Saw V","2017-10-30", "John Kramer is back", 7.2, false, 1);
    private JdbcMovieDao sut;
    private Movie testMovie;

    private JdbcGenreDao genre;
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcMovieDao(jdbcTemplate);
        genre = new JdbcGenreDao(jdbcTemplate);

    }

    @Test
    public void getMovies_returns_all_movies(){
        List<Movie> movies = sut.findAllMovies();

        Assert.assertEquals(3, movies.size() );
        assertMoviesMatch(MOVIE_1, movies.get(0));
        assertMoviesMatch(MOVIE_2, movies.get(1));
        assertMoviesMatch(MOVIE_3, movies.get(2));
    }
    @Test
    public void movie_returns_correct_genre(){
        int genreID = 2;
        // Get the list of movies by genre ID
        List<Movie> movies = sut.getMoviesByGenre(genreID);
        // Iterate through the movies
        for (Movie movie : movies) {
            Assert.assertEquals(MOVIE_2.getTitle(), movie.getTitle());
            //Checks and compares the titles of each movie
        }
        Genre genre2 = genre.getGenreById(genreID);
        //Searches the genre given by the genre ID of Movie_2

        Assert.assertEquals("Action", genre2.getGenreName());

    }

    @Test
    public void movie_returned_by_correct_id(){
        Movie movie = sut.getMovieById(201);
        assertMoviesMatch(movie, MOVIE_2);

        Movie movie2 = sut.getMovieById(202);
        assertMoviesMatch(movie2, MOVIE_3);
    }

    @Test
    public void movie_returns_null_when_id_is_not_found(){
        Movie movie = sut.getMovieById(1000);
        Assert.assertNull(movie);

        Movie movie2 = sut.getMovieById(0);
        Assert.assertNull(movie2);
    }

    @Test
    public void movie_returns_by_correct_title(){
        Movie movie = sut.findMovieByTitle("Barbie");
        assertMoviesMatch(movie, MOVIE_1);

        Movie movie2 = sut.findMovieByTitle("Spider-man");
        assertMoviesMatch(movie2, MOVIE_2);

    }

    @Test
    public void movies_that_are_favorite_are_only_returned(){
        Movie movie = sut.getFavoriteMovies(3);
        assertMoviesMatch(movie, MOVIE_2);
        //MOVIE_2 is set to true for favorite
    }

    @Test
    public void create_a_movie_returns_with_newId(){
        testMovie = new Movie(203, "Test","12-11-2000", "testing to see if this creates a movie", 8.5, true, 1 );
        Movie movie = sut.createFavorite(testMovie);

        int newId = movie.getMovieId();

        Assert.assertTrue(newId > 0);

        Movie retrievedMovie = sut.getMovieById(newId);
        assertMoviesMatch(movie, retrievedMovie);
    }




    private void assertMoviesMatch(Movie expected, Movie actual){
        Assert.assertEquals(expected.getMovieId(), actual.getMovieId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getReleaseDate(), actual.getReleaseDate());
        Assert.assertEquals(expected.getGenreId(), actual.getGenreId());
        Assert.assertEquals(expected.getOverview(), actual.getOverview());
        Assert.assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.1);
        Assert.assertEquals(expected.isFavorite(), actual.isFavorite());
    }
}
