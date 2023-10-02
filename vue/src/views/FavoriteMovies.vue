<template>
  <div>
       <div class="movie-container"  v-for="movie in movies" :key="movie.id">
        <div class="movie-details" >
          <h4>{{ movie.title }}</h4>
          <img :src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" />
          <h5>{{ movie.overview }}</h5>
          <br />
          <h5>Release Date: {{ movie.release_date }}</h5>
          <br />
          <h5>Rating: {{ movie.vote_average }} / 10</h5>
        </div>
        </div>
      </div>
</template>

<script>
import service from '../services/movieapiservice.js';
export default {
  name: 'favorites',
data() {
    return {
      movies: [],
    
    }
  },
  methods: {
      getUserFavorite() {
        const userId = this.$store.state.user.id
          service.getAllUserFavoriteMovies(userId).then(
              (response) => {
                 if (response.status === 201) {
                   this.movies = response.data;    
        console.log("Movie favorites have loaded ");
      } else {
        console.log("Failed to get all favorite movies");
      }
    })
    .catch((error) => {
      if (error.response) {
        console.error("Error grabbing favorite movies, response error", error.response);
      } else if (error.request) {
        console.error("Server error.", error.request);
      } else {
        console.error("An error occurred", error);
      }
    });
    
      }
  }

}
</script>

<style>

</style>