package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    //This class models the movie table off the SQL database, and references JSON properties from the external API movie database

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("genre_ids")
    private List<Integer> genreId;
    @JsonProperty("id")
    private int movieId;

    private String overview;


    @JsonProperty("poster_path")
    private String posterPath;

    private int favoriteId;

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    private String title;

    @JsonProperty("vote_average")
    private double voteAverage;
    private boolean isFavorite;

    private int userId;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    //Default constructor for it to be easier to create a new movie
    public Movie() {
    }

    //Constructor without id since it will serialize when a new movie is created
    public Movie( String title, String releaseDate, List<Integer> genreId, String overview, double voteAverage, boolean isFavorite) {
        this.releaseDate = releaseDate;
        this.genreId = genreId;
        this.overview = overview;
        this.title = title;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
    }

    public Movie(int movieId, String title, String releaseDate, List<Integer> genreId, String overview, double voteAverage, boolean isFavorite) {
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.title = title;
        this.genreId = genreId;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
    }
    public Movie(int movieId, String title, String releaseDate, String overview, double voteAverage, boolean isFavorite) {
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
    }

    public Movie( int movieId, String title, String releaseDate, String overview, double voteAverage, boolean isFavorite, int userId) {
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.overview = overview;
        this.title = title;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
        this.userId = userId;
    }
    public Movie( int movieId, String title, String releaseDate, String overview, double voteAverage, boolean isFavorite, int userId, String posterPath) {
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.overview = overview;
        this.title = title;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
        this.userId = userId;
        this.posterPath = posterPath;
    }

    public Movie( int movieId, String title, String releaseDate, String overview, double voteAverage, boolean isFavorite, int userId, String posterPath, int favoriteId) {
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.overview = overview;
        this.title = title;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
        this.userId = userId;
        this.posterPath = posterPath;
        this.favoriteId = favoriteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public List<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(List<Integer> genreId) {
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

