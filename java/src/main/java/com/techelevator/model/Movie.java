package com.techelevator.model;

public class Movie {
    private int movieId;
    private String title;
    private String director;
    private int releaseYear;
    private int genreId;
    private String plot;
    private String actors;
    private double averageRating;
    private boolean isFavorite;


    //Default constructor for it to be easier to create a new movie
    public Movie() {
    }


    //Constructor without the movieId, since adding a movie will automatically serialize the id
    public Movie(String title, String director, int releaseYear, int genreId, String plot, String actors, double averageRating, boolean isFavorite) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.plot = plot;
        this.actors = actors;
        this.averageRating = averageRating;
        this.isFavorite = isFavorite;
    }

    public Movie(int movieId, String title, String director, int releaseYear, int genreId, String plot, String actors, double averageRating, boolean isFavorite) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.plot = plot;
        this.actors = actors;
        this.averageRating = averageRating;
        this.isFavorite = isFavorite;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", genreId=" + genreId +
                ", plot='" + plot + '\'' +
                ", actors='" + actors + '\'' +
                ", averageRating=" + averageRating +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
