package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(value="http://localhost:8080")
@PreAuthorize("isAuthenticated()")

public class ProfileController {

    @Autowired
    private ProfileDao profileDao;



    @RequestMapping(path = "/profile/genres", method = RequestMethod.GET)
    public Profile getUserProfile(){
        return profileDao.getGenrePreferences();
    }

    //When the user clicks on "create profile, after entering their name
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Profile generateANewProfile(@RequestBody @Valid Profile profile){
        return profileDao.createAProfile(profile);
    }

}
