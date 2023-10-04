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


    //This will connect to our database and select all the movies that any user favorited, not specific by userid
    @Override
    public List<Movie> findAllMovies() {
        //Sql statements selects all columns in the movie table to show all movies and their info
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id, poster_path, favorite_id FROM movie;";
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


//This method will search for movies by a specific title in our database (movies added into our database are movies that were favorite(d) by users)
    @Override
    public Movie findMovieByTitle(String title) {
        Movie movie = null;
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id, poster_path, favorite_id FROM movie " +
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


    //This will search the movie by the serial ID implemented in the data table, finding a specific row of it and the columns identified or
    //associated with that ID
    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id, poster_path, favorite_id FROM movie " +
                "WHERE favorite_id = ?;";
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
    public List<Movie> getFavoriteMovies(int userId) {
        //This will only select movies if they are favorite(d) by the user, which will be all movies considering when that are inserted into our database
        //is_favorite is automatically set to true, although this method is specific to the user_id. This method is used in our front end
        // to show the favorite movies by only the user logged in

        List<Movie> results = new ArrayList<>();
        String sql = "SELECT movie_id, title, release_date, overview, vote_average, is_favorite, user_id, poster_path, favorite_id FROM movie WHERE is_favorite = true AND user_id = ?;";
        try{
            SqlRowSet queryResults = jdbcTemplate.queryForRowSet(sql, userId);
            //Users can have multiple favorite movies selected
            while(queryResults.next()){
                results.add(mapMovie(queryResults));
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
    public Movie createFavorite(Movie movie) {
        Movie favoriteMovie = null;
        //This is essentially inserting the movie from the external API from the database since the user favorites it and we want to retrieve it later on
        //We are inserting the movie_id because the external API already has movie_id's associated with them
        String sql = "INSERT INTO movie (movie_id, title, release_date, overview, vote_average, user_id, poster_path, is_favorite) VALUES (?,?,?,?,?,?,?,true) RETURNING favorite_id";
                int favorite = 0;

        try {
             favorite = jdbcTemplate.queryForObject(sql, int.class, movie.getMovieId() , movie.getTitle(), movie.getReleaseDate(), movie.getOverview(), movie.getVoteAverage(), movie.getUserId(), movie.getPosterPath());
            //Since this is creating a favorite movie, the boolean has to be true
           if(movie.isFavorite() == true) {
                   favoriteMovie = getMovieById(favorite);

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


    //If a user wants to remove their favorite movie from their favorites list, this method will be used to remove that movie off our database
    //under their desired user_id
    @Override
    public void deleteFavorite(int userId, int movieId) {
        String sql = "DELETE FROM movie WHERE user_id = ? AND movie_id = ?";
        try {
            jdbcTemplate.update(sql, userId, movieId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");
        } catch (BadSqlGrammarException e) {
            System.out.println("SQL statement isn't working");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
    }


    //Mapping method to connect the sql columns in our database to the Movie model class and constructor
    private Movie mapMovie(SqlRowSet row){
        Movie movie = new Movie();
        int movieId = row.getInt("movie_id");
        String title = row.getString("title");
        String releaseDate = row.getString("release_date");
        String overview = row.getString("overview");
        double voteAverage = row.getDouble("vote_average");
        boolean isFavorite = row.getBoolean("is_favorite");
        int userId = row.getInt("user_id");
        String posterPath = row.getString("poster_path");
        int favoriteId = row.getInt("favorite_id");

        movie = new Movie(movieId, title, releaseDate, overview, voteAverage, isFavorite, userId, posterPath, favoriteId);
        return movie;
    }
}
