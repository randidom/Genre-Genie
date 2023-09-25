package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcGenreDao implements GenreDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAllGenres() {
        return null;
    }

    @Override
    public Genre getGenreById(int id) {
        return null;
    }
}
