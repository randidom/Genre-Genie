<template>
  <div>
    <h2>Movie Search by Genre</h2>

    <input v-model="genreId" type="text" placeholder="Enter Genre ID" />
    <button @click="searchMoviesByGenre">Search</button>

    <ul>
      <li v-for="movie in movies" :key="movie.id">
        {{ movie.title }}
      </li>
    </ul>

  </div>
</template>

<script>
import service from '../services/movieapiservice.js'
export default {
  data() {
    return {
      genreId: '', 
      movies: []
    };
  },
  methods: {
    searchMoviesByGenre() {

      service.getMoviesByGenre(this.genreId).then(
          (response) => {
            this.movies = response.data.results;
          }
      );

  
    },
  },
};
</script>
