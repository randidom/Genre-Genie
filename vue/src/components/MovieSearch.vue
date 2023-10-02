<template>
  <div class="container">
    <h2>Movie Search by Title</h2>
    <input class="input" v-model="title" type="text" placeholder="Enter Title" />
    <div v-show="errorMessage" class="error-message">{{ errorMessage }}</div>
    <button class="search-button" @click="searchMoviesByTitle">Search</button>
    <div class="movie-list">
      
      <!-- Use a separate grid for each movie -->
      <div v-for="(movie, index) in movies" :key="movie.id" class="movie-container">
        <div class="content">
          <img :src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" />
          <h4 class="movie-title">{{ movie.title }}</h4> <!-- Apply margin or padding to the movie title -->
          <h5>{{ movie.overview }}</h5>
          <br />
          <h5>Release Date: {{ movie.release_date }}</h5>
          <br />
          <h5>Rating: {{ movie.vote_average }} / 10</h5>
           </div>
        <div class="button-container">
          <button class="favorite-button" @click.prevent="addMovieToFavorites(index)" :class="{ 'added': favorites.includes(movie.id) }">
            {{ favorites.includes(movie.id) ? 'Added to Favorites' : 'Add to Favorites' }}
          </button>
        </div>
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
      favorites: [],
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
    addMovieToFavorites(index) {
  if (this.movies.length === 0) {
    // Handle the case where there are no movies to add as favorites.
    return;
  }
   const movieId = this.movies[index].id;
   // Updates the button text to "Added to Favorites", so it shows the user it has been already added
      this.$forceUpdate(); // Force a re-render to update the button text

  // Check if the movie ID already exists in favorites to not get an error in the backend
  // for duplicate id's
  if (!this.favorites.includes(movieId)) {
    const movieToAdd = {
      id: movieId,
      title: this.movies[index].title,
      release_date: this.movies[index].release_date,
      overview: this.movies[index].overview,
      vote_average: this.movies[index].vote_average,
      userId: this.$store.state.user.id,
      poster_path: this.movies[index].poster_path,
      is_favorite: true,
      
      
    };

    // Add the movie to favorites array
    this.favorites.push(movieId);

 //Adds the movie info to our database
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
  }
};
</script>

<style scoped>

.container {
  text-align: center;
  padding: 20px;
  margin: 0 auto;
  width: 90%;
  max-width: 1700px;
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -50%);
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
  font-family: monospace;
  font-size: 25px;

}

.movie-list {
  display: grid;
  flex-wrap: wrap;
  justify-content: space-between;
  position: fixed;
  font-family: serif;
  width: 100%;
  margin-top: 20px;
  right: 0px;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 60px ;
}

.movie-container {
  flex-basis: calc(20% - 10px);
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 0 0 5px rgba(44, 43, 43, 0.1);
  background-color: rgb(22, 28, 117);
  color: #d5e9fd;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 100;
  background-color:  rgba(22, 29, 117, 0.5); 
  padding: 50px;
  border-radius: 5px; 
  justify-content: space-between;
  height: 100%;

}
.movie-title {
  margin-bottom: 50px; /* Adjust the value as needed */
}
.movie-details {
  padding: 40px;
  text-align: center;
}



.movie-details h4 {
  margin: 0;
}

.movie-container img {
  max-width: 75%;
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
  background-color:rgb(248, 163, 5);
  width: 180px;
  color: #000;
  font-size: 12px;
  padding: 12px 0;
  border: 0;
  cursor: pointer;
  border-radius: 0;
  outline: none;
  justify-content: center; 
}

.button-container {
  justify-content: space-around;
  display: flex-end;
  margin-top: 20px;
}

.favorite-button:hover {
 background-color: rgb(18, 18, 49);
  color: #fff;
}
.favorite-button.added {
  background-color: rgb(18, 18, 49);
  color: #fff;
}
</style>