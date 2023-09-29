package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfileDao implements ProfileDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Profile getGenrePreferences() {
       Profile profile = null;

        String sql = "SELECT profile.profile_id, profile.user_id, profile.name, users.username, genre.genre_name " +
        "FROM profile " +
                "JOIN users ON profile.user_id = users.user_id " +
                "JOIN profile_genre ON profile.profile_id = profile_genre.profile_id " +
                "JOIN genre ON profile_genre.genre_id = genre.genre_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

            while (results.next()) {
                String genreName = results.getString("genre_name");
                int profileID = results.getInt("profile_id");
                int userID = results.getInt("user_id");
                String name = results.getString("name");
                String username = results.getString("username");

                profile = new Profile(profileID, userID, name,username, genreName );

            }
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");

        }catch (BadSqlGrammarException e){
            System.out.println("SQL statement isn't working");
        }catch (DataIntegrityViolationException e){
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }

        return profile;
    }



    @Override
    public Profile createAProfile(Profile profile) {
        Profile newProfile = null;

        String sql = "INSERT INTO profile (user_id, name) VALUES (?, ?) RETURNING profile_id;";
        int newProfileID = 0;


        try {
             newProfileID = jdbcTemplate.queryForObject(sql, int.class, profile.getUserId(), profile.getName());

            newProfile = getProfileById(newProfileID);

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");
        } catch (BadSqlGrammarException e) {
            System.out.println("SQL statement isn't working");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        if (profile.getGenreIds() != null) {
            for (Integer genreId : profile.getGenreIds()) {
                String sql2 = "INSERT INTO profile_genre (profile_id, genre_id) VALUES (?,?)";
                jdbcTemplate.update(sql2, newProfileID, genreId);
            }
        }
        return newProfile;
    }

    @Override
    public Profile getProfileById(int id) {
        Profile profile = null;
        String sql = "SELECT user_id, profile_id, name FROM profile WHERE user_id = ?";;
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()) {
                int profileID = results.getInt("profile_id");
                int userID = results.getInt("user_id");
                String name = results.getString("name");

                profile = new Profile(profileID, userID, name);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database is down.");
        } catch (BadSqlGrammarException e) {
            System.out.println("SQL statement isn't working");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Issue with primary key or foreign key, or a violation of our constraints.");
        }
        return profile;
    }

}
