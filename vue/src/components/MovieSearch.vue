<template>
  <div class="container">
    <h2>Movie Search by Title</h2>
    <input v-model="title" type="text" placeholder="Enter Title" />
    <div v-show="errorMessage" class="error-message">{{ errorMessage }}</div>
    <button class="search-button" @click="searchMoviesByTitle">Search</button>
    <div class= "movie-container" v-for="movie in movies" :key="movie.id">
      <div class="movie-details">
        <h4>{{ movie.title }}</h4>
         <img v-bind:src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster"/>
         <h5>{{movie.overview}}</h5>
         <br/>
         <h5>Release Date: {{movie.release_date}}</h5>
         <br/>
         <h5>Rating: {{movie.vote_average}} / 10</h5>
         </div>
         <button id="favorite-button">Add as Favorite</button>
      
    </div>
  </div>
</template>

<script>
import service from '../services/movieapiservice.js'
export default {
  data() {
    return {
      title: '', 
      movies: []
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
    
  },
};
</script>

<style>
.container {
  background-color: rgba(207, 223, 238, 0.9);
  text-align: center;
  padding: 20px;
  border-radius: 10px;
  margin: 0 auto;
  width: 90%;
  max-width: 600px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  backdrop-filter: blur(10px);
}

.movie-container {
  width: calc(50% - 10px); /* 50% width for each movie container with some spacing */
  margin-bottom: 20px; /* Add space between rows */
  background-color: rgb(255, 255, 255); /* Background color for each movie container */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
  border-radius: 5px; /* Rounded corners */
}

.movie-details {
  padding: 10px;
  text-align: left;
}

.movie-details h4 {
  margin: 0;
}

.movie-container img {
  max-width: 100%; /* Allow images to scale within their container */
}
.search-button{
   background-color: #425b75;
}

.favorite-button {
  background-color: #425b75;
  color: #fff;
  padding: 5px 10px; /* Adjust button padding as needed */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%; /* Make the button the same width as the container */
  font-size: 13px;
}

.favorite-button:hover {
  background-color: #0056b3;
}
</style>