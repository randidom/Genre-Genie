package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

public class JdbcProfileDaoTests extends BaseDaoTests{



    private final Profile PROFILE_1 = new Profile(1, 1, "Randi");
    private final Profile PROFILE_2 = new Profile(2, 2, "Jim");
    private  final Profile PROFILE_3 = new Profile(3, 3, "Sara");


    private JdbcProfileDao sut;

    private Profile testProfile;
    private JdbcUserDao user;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcProfileDao(jdbcTemplate);

        user = new JdbcUserDao(jdbcTemplate);

    }

    @Test
    public void getProfileInfo(){
        Profile profile = sut.getGenrePreferences();

        Assert.assertEquals(PROFILE_3.getProfileId(), profile.getProfileId());
        Assert.assertEquals(PROFILE_3.getUserId(), profile.getUserId());
        Assert.assertEquals(PROFILE_3.getName(), profile.getName());

    }

    @Test
    public void id_returns_correct_profile(){
        Profile profile = sut.getProfileById(1);
        assertProfilesMatch(profile,PROFILE_1);

        Profile profile2 = sut.getProfileById(2);
        assertProfilesMatch(profile2, PROFILE_2);

    }

    @Test
    public void profile_gets_created(){


        testProfile = new Profile(0, 4, "Test");

        Profile profile = sut.createAProfile(testProfile);

        int newId = profile.getProfileId();

        Assert.assertTrue(newId > 0);

        Profile retrievedProfile = sut.getProfileById(newId);
        assertProfilesMatch(profile, retrievedProfile);
    }

    private void assertProfilesMatch(Profile expected, Profile actual){
        Assert.assertEquals(expected.getGenreName(), actual.getGenreName());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getProfileId(), actual.getProfileId());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());

    }


}
