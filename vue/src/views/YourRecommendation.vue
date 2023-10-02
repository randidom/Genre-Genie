<template>
  <div>
    <div class="Title">
      <h1> Movie Recommendations</h1>
    </div>
    <div class="scrolling-container">
    <div class="container">
      <div class="Ultimate-grid">
        <div class="item" v-for="(movie, index) in movies" :key= "movie.id">
          <div class="content">
            <img v-bind:src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" width="200"/>
            <h3>{{movie.title}}</h3>
            <br>{{movie.overview}}
          </div>
          <div class="button-container">
            <button class="button1" style="margin-top: 10px;" @click="addToFavorites(index)" :class="{ 'added': selectedMovies.includes(movie.id) }">
            {{ selectedMovies.includes(movie.id) ? 'Added to Favorites' : 'Add to Favorites' }}
            </button>
          </div>
        </div>
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
      movies: [],
      selectedMovies: [] // New data property to store selected movies
    }
  },
  methods: {
    recomendations() {
      service.getAllMovies().then(response => {
        this.movies = response.data
      })
    },
    addToFavorites(index) {
      if (this.movies.length === 0) {
    // Handle the case where there are no movies to add as favorites.
    return;
  }
   const movieId = this.movies[index].id;
   // Updates the button text to "Added to Favorites", so it shows the user it has been already added
      this.$forceUpdate(); // Force a re-render to update the button text

  // Check if the movie ID already exists in favorites to not get an error in the backend
  // for duplicate id's
  if (!this.selectedMovies.includes(movieId)) {
    const movieToAdd = {
      id: movieId,
      title: this.movies[index].title,
      release_date: this.movies[index].release_date,
      overview: this.movies[index].overview,
      vote_average: this.movies[index].vote_average,
      userId: this.$store.state.user.id,
      is_favorite: true,
    };
    // Add the movie to favorites array
    this.selectedMovies.push(movieId);

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
  },
  created() {
    this.recomendations();
  },
  name: "AddFavorite",
};
</script>
<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding:20px;
  margin: 30px;
  width: 100%;
  min-height: 100vh;
  
  
}
.scrolling-container{
  display: flex;
 flex-direction: column;
 align-items: center;
 justify-content: center;
 height:100vh;

}


.Ultimate-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 60px;
  max-width: 100vw;
  padding: 20px;
  height: 60vh;
}
.item {
  background-color: rgba(22, 29, 117, 0.5);
  color: white;
  border-radius: 10px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.content {
  padding: 20px;
}
.button-container {
    justify-content: center; 
  margin-top: 20px;
}
.button1 {
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
.button1:hover {
  background-color: rgb(223, 190, 102);

}

.Title {
  position: fixed;
  text-align: left;
  margin: 20px;
  left: 20px;
  color: white;
  font-size: 30px;
}

.button1.added {
  background-color: rgb(18, 18, 49);
  color: #fff;
}

</style>







