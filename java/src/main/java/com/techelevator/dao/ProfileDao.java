package com.techelevator.dao;

import com.techelevator.model.Profile;

import java.util.List;

public interface ProfileDao {

    Profile getGenrePreferences ();
   // This will grab the genre names associated with the genreIds found on the profile table (genreId is a foreign key) and also grab all the components + information
    //associated with an user's profile


    Profile createAProfile(Profile profile);
    //This will create a profile once a user makes account

    Profile getProfileById(int id);
    //This searches the profile by the user id

}
