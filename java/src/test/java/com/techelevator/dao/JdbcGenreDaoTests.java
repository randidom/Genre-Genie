package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcGenreDaoTests extends BaseDaoTests{

    private final Genre GENRE_1 = new Genre(1, "Comedy");
    private final Genre GENRE_2 = new Genre(2, "Action");
    private final Genre GENRE_3 = new Genre(3, "Horror");

    private JdbcGenreDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcGenreDao(jdbcTemplate);
    }

    @Test
    public void all_genre_names_are_returned(){
        List<Genre> genres = sut.getAllGenres();
        Assert.assertEquals(3, genres.size());
        assertGenresMatch(GENRE_1, genres.get(0));
        assertGenresMatch(GENRE_2, genres.get(1));
        assertGenresMatch(GENRE_3, genres.get(2));

    }

    @Test
    public void genre_name_is_found_by_id_correctly(){
        Genre genre = sut.getGenreById(1);
        assertGenresMatch(genre, GENRE_1);

        Genre genre2 = sut.getGenreById(2);
        assertGenresMatch(genre2, GENRE_2);
    }

    @Test
    public void genre_returns_null_when_id_is_not_found(){
        Genre genre = sut.getGenreById(999);
        Assert.assertNull(genre);

        Genre genre2 = sut.getGenreById(0);
        Assert.assertNull(genre2);
    }


    private void assertGenresMatch(Genre expected, Genre actual){
        Assert.assertEquals(expected.getGenreId(), actual.getGenreId());
        Assert.assertEquals(expected.getGenreName(), actual.getGenreName());
    }

}
