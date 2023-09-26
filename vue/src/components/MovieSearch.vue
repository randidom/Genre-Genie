<template>
  <div class="container">
    <h2>Movie Search by Genre</h2>
    <input v-model="genreId" type="text" placeholder="Enter Genre" />
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
button {
  background-color: #425b75;
  color: #fff;
  padding: 10px 20px;
  border: none; 
  border-radius: 5px;
  cursor: pointer; 
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}

ul {
  list-style: none;
  padding: 0; 
  text-align: left;
}

ul li {
  margin: 10px 0;
}
</style>