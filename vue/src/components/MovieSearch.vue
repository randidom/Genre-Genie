<template>
  <div class="container">
    <h2>Movie Search by Title</h2>
    <input class="input" v-model="title" type="text" placeholder="Enter Title" />
    <div v-show="errorMessage" class="error-message">{{ errorMessage }}</div>
    <button class="search-button" @click="searchMoviesByTitle">Search</button>
    <div class="movie-list">
      <div class="movie-container"  v-for="(movie,index) in movies" :key="movie.id">
        <div class="movie-details" >
          <h4>{{ movie.title }}</h4>
          <img :src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" />
          <h5>{{ movie.overview }}</h5>
          <br />
          <h5>Release Date: {{ movie.release_date }}</h5>
          <br />
          <h5>Rating: {{ movie.vote_average }} / 10</h5>
        </div>
        <button class="favorite-button" @click.prevent="addMovieToFavorites(index)">Add as Favorite</button>
      </div>
    </div>
  </div>
</template>

<script>
import service from '../services/movieapiservice.js'
export default {
  data() {
    return {
      errorMessage: null,
      title: '', 
      movies: [],
    };
  },
  methods: {
    searchMoviesByTitle() {
      service.getMoviesByTitle(this.title).then(
          (response) => {
             if (response.data.length === 0) {
        this.movies = []; // Clear any previous search results
        this.errorMessage = "Sorry, no movie by that title was found. Please enter another title.";
      } else {
        this.movies = response.data;
        this.errorMessage = null; // Clear any previous error message
      }
       }
      );
    },
    //TODO: This doesn't work and isn't adding the movie to our database
    //Also page shouldn't refresh and go back to search when clicking favs
    addMovieToFavorites(index) {
  if (this.movies.length === 0) {
    
    // Handle the case where there are no movies to add as favorites.
    return;
  }

  const movieToAdd = {
    id: this.movies[index].id,
    title: this.movies[index].title, 
    release_date: this.movies[index].release_date,
    overview: this.movies[index].overview,
    vote_average: this.movies[index].vote_average,
    is_favorite: true,
  };

  service.createAFavoriteMovie(movieToAdd)
    .then((response) => {
      if (response.status === 201) {
        
        // Successfully added to favorites
        console.log("Movie added to favorites successfully");
      } else {
        console.log("Failed to add movie to favorites");
      }
    })
    .catch((error) => {
      if (error.response) {
        console.error("Error submitting favorite, response error", error.response);
      } else if (error.request) {
        console.error("Server error.", error.request);
      } else {
        console.error("An error occurred", error);
      }
    });
}
  }
};
</script>

<style scoped>

.container {
  background-color: rgba(22, 29, 117, 0.5);
  text-align: center;
  padding: 20px;
  margin: 0 auto;
  width: 90%;
  max-width: 1700px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -50%);
  backdrop-filter: blur(30px);
  font-family: monospace;
  font-size: 20px;
  color:#fff;
}

.input{
  color: #fff;
  text-align: center;
  width: 50%;
  margin: auto;
  display: block;
}

.movie-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  position: fixed;
  font-family: serif;
  width: 100%;
  margin-top: 20px;
  right: 0px;
  background-color: rgb(118, 228, 255);
}

.movie-container {
  flex-basis: calc(20% - 0px);
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 0 0 5px rgba(44, 43, 43, 0.1);
  background-color: rgb(22, 28, 117);
  border: 1px solid black;
  color: #d5e9fd;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 100;
}

.movie-details {
  padding: 10px;
  text-align: left;
}

.movie-details h4 {
  margin: 0;
}

.movie-container img {
  max-width: 100%;
}

.search-button {
  width: 25%;
  font-size: 20px;
  background-color:rgb(248, 163, 5);
  margin-top: 15px;
  border-radius: 0px;
}
.search-button:hover{
  background-color: rgb(18, 18, 49);
  color: #fff;
}

.favorite-button {
  background-color: rgb(248, 163, 5);
  color: rgb(18, 20, 51);
  padding: 5px 10px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 13px;
  border-radius: 0;
  margin-top: auto; 
}

.favorite-button:hover {
 background-color: rgb(18, 18, 49);
  color: #fff;
}
</style>