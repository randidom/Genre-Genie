package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:8080")
@PreAuthorize("isAuthenticated()")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    private RestTemplate restTemplate = new RestTemplate();



    //This method will just grab the list of genre's with the id attached with the genre name. For example, genre id 27 is action
    //This method also is not used in our front end code, but it is here if we want to implement it instead of hard coding the name and genre_ids associated together
    @RequestMapping(path = "/movies/genre/list", method = RequestMethod.GET)
    public List<GenreResults> getAllGenreEndpoints() {
        List<GenreResults> genre = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();

        header.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Yzk1NGEwYjE5MzlhMjM1YWVjMTBlNjdkNWNjOWI1OCIsInN1YiI6IjY1MTFlZDc0MjZkYWMxMDBlYjFiMmE0ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.oLsJCB9cKnuZYVF5PzCebIJh93zXFzoHQAXc7piQSl0");
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(header);

        GenreResponse response = restTemplate.exchange("https://api.themoviedb.org/3/genre/movie/list" , HttpMethod.GET, entity, GenreResponse.class).getBody();
        genre = response.getAllGenres();

        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The list of genre's has not been found.");
        } else {
            return genre;
        }

    }

    //This method will grab the genre id's attached to the user id's aka the genre preferences of each user
    //This method will be used in the front end to grab those genre_id's and then populate movie recommendations
    //based on those genre_ids
    @RequestMapping(path = "/profile/genres/{userId}", method = RequestMethod.GET)
    public Genre userGenrePreferences(@PathVariable int userId){
        return genreDao.getGenrePreferences(userId);
    }


    //This method will be used after the user registers, this wil prompt the user to select up to 3 genres and have them
    //select genres based off their id's, so they are associated with that account
    //We don't need to return anything necessarily, so it is a void method
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path= "/create", method = RequestMethod.POST)
    public void createGenrePreferences(@RequestBody @Valid Genre genre, Principal principal){
         genreDao.addGenresToUser(genre, principal);
    }

}

