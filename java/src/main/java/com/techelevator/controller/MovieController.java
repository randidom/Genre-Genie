package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import com.techelevator.model.Response;
import com.techelevator.model.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value="http://localhost:8080")
@PreAuthorize("isAuthenticated()")
public class MovieController {

    @Autowired
    private MovieDao movieDao;
    private RestTemplate restTemplate = new RestTemplate();


    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    public List<Results> getAllMovieEndpoint() {
        List<Results> movies = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();
        //headers are used to transmit additional information about the request or response
        //This sets the bearer token for all methods and allows for authorization, so it is not needed manually in the URL
        header.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Yzk1NGEwYjE5MzlhMjM1YWVjMTBlNjdkNWNjOWI1OCIsInN1YiI6IjY1MTFlZDc0MjZkYWMxMDBlYjFiMmE0ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.oLsJCB9cKnuZYVF5PzCebIJh93zXFzoHQAXc7piQSl0");
        header.setContentType(MediaType.APPLICATION_JSON);
        //the content of the HTTP request or response is in JSON (JavaScript Object Notation) format from the external API)

        HttpEntity entity = new HttpEntity<>(header);
        Response response = restTemplate.exchange("https://api.themoviedb.org/3/discover/movie", HttpMethod.GET, entity, Response.class).getBody();
            // The first argument is the URL to which the request is being made.
            // The second argument is the HTTP method, which is GET in this case.
            // The third argument is the HttpEntity object entity, which contains the headers and potentially other information for the request.
            // The fourth argument is the expected response type, which is Response.class in this case.
            //.getBody() is used to extract the body of the HTTP response as an instance of the Response class. Response is the class to represent the response from the external API.
        movies = response.getAllMovies();
            //If no movies appear, then put this error they that are not found
        if (movies == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movies are not found.");
        } else {
            return movies;
        }
    }

    @RequestMapping(path = "/movies/genre/recs", method = RequestMethod.GET)
    public List<Results> getMoviesBySelectedGenre(@RequestBody @Valid Genre genre) {

        List<Results> movies = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();

        header.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Yzk1NGEwYjE5MzlhMjM1YWVjMTBlNjdkNWNjOWI1OCIsInN1YiI6IjY1MTFlZDc0MjZkYWMxMDBlYjFiMmE0ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.oLsJCB9cKnuZYVF5PzCebIJh93zXFzoHQAXc7piQSl0");

        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(header);
        for(int id: genre.getGenreIds()) {
            Response response = restTemplate.exchange("https://api.themoviedb.org/3/discover/movie?with_genres=" + id, HttpMethod.GET, entity, Response.class).getBody();
            movies.addAll(response.getAllMovies());
        }
        if (movies.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movies are not found for this genre.");
        } else {
            return movies;
        }

    }

    @RequestMapping(path = "/movies/titles/{title}", method = RequestMethod.GET)
    public List<Results> getMoviesByTitleSearch(@PathVariable String title) {
        List<Results> movies = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();

        header.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Yzk1NGEwYjE5MzlhMjM1YWVjMTBlNjdkNWNjOWI1OCIsInN1YiI6IjY1MTFlZDc0MjZkYWMxMDBlYjFiMmE0ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.oLsJCB9cKnuZYVF5PzCebIJh93zXFzoHQAXc7piQSl0");
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(header);

        Response response = restTemplate.exchange("https://api.themoviedb.org/3/search/movie?query=" + title, HttpMethod.GET, entity, Response.class).getBody();
        movies = response.getAllMovies();


        if (movies == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found for this title.");
        } else {
            return movies;
        }
    }



    //This method should grab the movie from the external API and then take that movie and do an insert into our movie table, making the is_favorite true
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorites", method = RequestMethod.POST)
    public Movie updateMovieToFavorite(@RequestBody @Valid Movie movie) {
        return movieDao.createFavorite(movie);
    }


    //This method will grab the movies in the movie table from the method above and grab the user favorite movies
    @RequestMapping(path = "/favorites/movies/{userId}", method = RequestMethod.GET)
    public Movie getUserFavoriteMovies(Integer userId) {
        return movieDao.getFavoriteMovies(userId);
    }



}

