package com.techelevator.dao;

import com.techelevator.model.Movie;
import com.techelevator.model.Results;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcMovieDao implements MovieDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> findAllMovies() {
        //Sql statements selects all columns in the movie table to show all movies and their info
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id FROM movie;";
        List<Movie> results = new ArrayList<>();

        try{
            SqlRowSet queryResults = jdbcTemplate.queryForRowSet(sql);
            while(queryResults.next()){
                Movie current = mapMovie(queryResults);
                results.add(current);
            }
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");

        }catch (BadSqlGrammarException e){
            System.out.println("SQL statement isn't working");
        }catch (DataIntegrityViolationException e){
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return results;
        }


    @Override
    public List<Movie> getMoviesByGenre(int genre) {
        List<Movie> results = new ArrayList<>();
        //Since the genreID is a foreign key on the movie table, have to join the genre table for where that foreign key/primary key meet to find the name of the genreId
        //associated with that movie
        String sql = "SELECT movie.movie_id AS movie_id, title, release_date, overview, vote_average, user_id, is_favorite FROM movie " +
                "JOIN movie_genre ON movie.movie_id = movie_genre.movie_id WHERE movie_genre.genre_id = ?;";
        try {
            SqlRowSet queryForRowSet = jdbcTemplate.queryForRowSet(sql, genre);
            while (queryForRowSet.next()){
                Movie current = mapMovie(queryForRowSet);
                results.add(current);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");

        }catch (BadSqlGrammarException e){
            System.out.println("SQL statement isn't working");
        }catch (DataIntegrityViolationException e){
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return results;
    }

    @Override
    public Movie findMovieByTitle(String title) {
        Movie movie = null;
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id FROM movie " +
                "WHERE title LIKE ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);
            //There can be many movies with the same title, for example sequel movies (Grown Ups, Grown Ups 2, Twilight)
            //Therefore, there can be more than one result in the search
            while(results.next()){
                movie = mapMovie(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");

        }catch (BadSqlGrammarException e){
            System.out.println("SQL statement isn't working");
        }catch (DataIntegrityViolationException e){
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return movie;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id FROM movie " +
                "WHERE movie_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()){
                movie = mapMovie(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");
        }catch (BadSqlGrammarException e){
            System.out.println("SQL statement isn't working");
        }catch (DataIntegrityViolationException e){
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return movie;

    }


    @Override
    public Movie getFavoriteMovies(int userId) {
        //This will only select movies if they are favorite(d) by the user
        Movie movie = null;
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id FROM movie WHERE is_favorite = true AND user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            //Users can have multiple favorite movies selected
            while(results.next()){
                movie = mapMovie(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
        System.out.println("Database is down.");

         }catch (BadSqlGrammarException e){
        System.out.println("SQL statement isn't working");
         }catch (DataIntegrityViolationException e){
        System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
         }
        return movie;

    }

    @Override
    public Movie createFavorite(Movie movie) {
        Movie favoriteMovie = null;
        //This is essentially inserting the movie from the external API from the database since the user favorites it and we want to retrieve it later on
        //We are inserting the movie_id because the external API already has movie_id's associated with them
        String sql = "INSERT INTO movie (movie_id, title, release_date, overview, vote_average, user_id, is_favorite) VALUES (?,?,?,?,?,?,true) RETURNING movie_id";
                int movieId = 0;
        try {
             movieId = jdbcTemplate.queryForObject(sql, int.class, movie.getMovieId() , movie.getTitle(), movie.getReleaseDate(), movie.getOverview(), movie.getVoteAverage(), movie.getUserId());
            //Since this is creating a favorite movie, the boolean has to be true
           if(movie.isFavorite() == true) {
                   favoriteMovie = getMovieById(movieId);

               }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");
        } catch (BadSqlGrammarException e) {
            System.out.println("SQL statement isn't working");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }

        return favoriteMovie;
    }

    private Movie mapMovie(SqlRowSet row){
        Movie movie = new Movie();
        int movieId = row.getInt("movie_id");
        String title = row.getString("title");
        String releaseDate = row.getString("release_date");
        String overview = row.getString("overview");
        double voteAverage = row.getDouble("vote_average");
        boolean isFavorite = row.getBoolean("is_favorite");
        int userId = row.getInt("user_id");

        movie = new Movie(movieId, title, releaseDate, overview, voteAverage, isFavorite, userId);
        return movie;
    }
}
