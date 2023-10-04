package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenreDao implements GenreDao{

    private final JdbcTemplate jdbcTemplate;



    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }


    //This will just get a list from our database of the genre_id's and the name of the genre attached to it
    //This is not implemented in our front end code or controller class, since there is already an external API link
    // that shows the list of genres found in our controller found
    @Override
    public List<Genre> getAllGenres() {
        String sql = "SELECT genre_id, genre_name FROM genre;";
        List<Genre> results = new ArrayList<>();
        try{
            SqlRowSet queryResults = jdbcTemplate.queryForRowSet(sql);
            while(queryResults.next()){
                Genre current = mapGenre(queryResults);
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


    //This will get the genre name associated with the id in our database. This method won't really be used unless we want to the id by the name.
    //This method is not implemented in the controller class and is just implemented here for testing purposes in our data base and if we want to connect
    //the genre id associated with the genre name
    @Override
    public Genre getGenreById(int id) {
        Genre genre = null;
        String sql = "SELECT genre_id, genre_name FROM genre WHERE genre_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()){
                genre = mapGenre(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");

        }catch (BadSqlGrammarException e){
            System.out.println("SQL statement isn't working");
        }catch (DataIntegrityViolationException e){
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return genre;
    }


    //This method will be used to find the genre_id's associated with the user_id after they are done
    //selecting their genre preferences.
    //This method will connect to the external API in the front end and connect the genre id's based
    //on the user profile to the matching movie's that have those genre_ids
    @Override
    public Genre getGenrePreferences(int id) {
        Genre genre = null;
        List<Integer> genreList = new ArrayList<>();
        String sql = "SELECT user_genre.genre_id, genre_name, user_id FROM user_genre JOIN genre ON" +
                " genre.genre_id = user_genre.genre_id WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {

                int genreId = results.getInt("genre_id");
                int userId = results.getInt("user_id");
                genre = new Genre(genreId, userId);
                genreList.add(genreId);
            }
            genre.setGenreIds(genreList);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");

        } catch (BadSqlGrammarException e) {
            System.out.println("SQL statement isn't working");

        } catch (DataIntegrityViolationException e) {
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return genre;
    }


    //This method will be used when after a user registers and logins in, they will be prompted to select
    // their genre preferences, which will be tied based of the user_id
    //Users can have multiple genre_id's associated with one single user_id

    @Override
    public void addGenresToUser(Genre genre, Principal principal) {
        String sql2 = "UPDATE users SET is_activated = true WHERE username = ?";
        String sql = "INSERT INTO user_genre (user_id, genre_id) VALUES (?, ?) RETURNING genre_id";
        int generatedGenreId = 0;
        try {
            for(int genreId : genre.getGenreIds()) {
                generatedGenreId = jdbcTemplate.queryForObject(sql, int.class, genre.getUserId(), genreId);
            }
            jdbcTemplate.update(sql2, principal.getName());

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");
        } catch (BadSqlGrammarException e) {
            System.out.println("SQL statement isn't working");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");

        }
  }

    //Mapping method will tie to the constructor used in our model class to the sql tables
    private Genre mapGenre(SqlRowSet row){
        Genre genre = new Genre();
        int genreId = row.getInt("genre_id");
        String genreName = row.getString("genre_name");


        genre = new Genre(genreId, genreName);
        return genre;
    }
}
