package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    Profile getGenrePreferences ();
    //SELECT genre_name FROM profile JOIN genre ON profile_genre_id = genre_id;
}
