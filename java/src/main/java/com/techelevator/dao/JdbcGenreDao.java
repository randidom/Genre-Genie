package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenreDao implements GenreDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    private Genre mapGenre(SqlRowSet row){
        Genre genre = new Genre();
        int genreId = row.getInt("genre_id");
        String genreName = row.getString("genre_name");

        genre = new Genre(genreId, genreName);
        return genre;
    }
}
