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
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:8080")
@PreAuthorize("isAuthenticated()")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    private RestTemplate restTemplate = new RestTemplate();


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

    @RequestMapping(path = "/profile/genres/{userId}", method = RequestMethod.GET)
    public Genre userGenrePreferences(@PathVariable int userId){
        return genreDao.getGenrePreferences(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path= "/create", method = RequestMethod.POST)
    public List<Genre> createGenrePreferences(@RequestBody @Valid Genre genre){
        return genreDao.addGenresToUser(genre);
    }

}

