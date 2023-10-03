<template>
  <div class="container">
    <h2>Movie Search by Title</h2>
    <div class="search-bar">
      <input class="input" v-model="title" type="text" placeholder="Enter Title" />
      <button class="search-button" @click="searchMoviesByTitle">Search</button>
    </div>
    <div v-show="errorMessage" class="error-message">{{ errorMessage }}</div>
    <div class="movie-list">
      <div v-for="(movie, index) in movies" :key="movie.id" class="movie-container">
        <div class="content">
          <img :src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" />
          <h4 class="movie-title">{{ movie.title }}</h4>
          <h5>{{ movie.overview }}</h5>
          <br />
           </div>
        <div class="button-container">
          <button class="favorite-button" @click.prevent="addMovieToFavorites(index)"
          :class="{ 'remove-favorite': favorites.includes(movie.id) }"
      >
        {{ favorites.includes(movie.id) ? 'Remove from Favorites' : 'Add to Favorites' }}
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
        this.movies = []; //Clear any previous search results
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
    //Handle the case where there are no movies to add as favorites.
    return;
  }

  const movie = this.movies[index];
  const movieId = movie.id;

  if (this.favorites.includes(movieId)) {
    //If the movie is in favorites, remove it
    const movieIndex = this.favorites.indexOf(movieId);
    this.favorites.splice(movieIndex, 1);

    //Remove the movie from the database
    service.deleteMovieFromFav(this.$store.state.user.id, movieId)
      .then((response) => {
        if (response.status === 200) {
          console.log("Movie removed from favorites successfully");
        } else {
          console.error("Failed to remove movie from favorites");
        }
      })
      .catch((error) => {
        console.error("An error occurred", error);
      });
  } else {
    //If the movie is not in favorites, add it
    this.favorites.push(movieId);

    const movieToAdd = {
      id: movieId,
      title: movie.title,
      release_date: movie.release_date,
      overview: movie.overview,
      vote_average: movie.vote_average,
      userId: this.$store.state.user.id,
      poster_path: movie.poster_path,
      is_favorite: true,
    };

    //Add the movie info to our database
    service.createAFavoriteMovie(movieToAdd)
      .then((response) => {
        if (response.status === 201) {
          console.log("Movie added to favorites successfully");
        } else {
          console.error("Failed to add movie to favorites");
        }
      })
      .catch((error) => {
        console.error("An error occurred", error);
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
  left: 45%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  color:#fff;
}
.search-bar {
  display: flex;
  align-items: center; 
  justify-content: center;
  margin-bottom: 20px;
}
.input{
  color: #fff;
  text-align: center;
  width: 50%;
  margin: auto;
  display: block;
  font-family: monospace;
  font-size: 25px;
    flex: 1;
 margin-bottom: 2px;
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
  gap: 60px;
  margin-top: 10%;
margin-right: -5%;
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
  margin-bottom: 50px;
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
  width: 30%;
  font-size: 16px;
  background-color:rgb(248, 163, 5);
  margin-top: 15px;
  border-radius: 0px;
  margin-left: 30px;
  display: inline-block;
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
  text-align: center;
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
.favorite-button.remove-favorite {
  background-color: rgb(18, 18, 49);
  color: #fff;
}
@media screen and (max-width: 768px){
  .container{
    display: grid;
    grid-template-areas: 'headline' 'input' 'search-button';
    transform: translate( -45%, -20%);
    gap: 10%;
  }
  .input{
  grid-area: input;
  width: 60%;
  
    
  }
  h2{
    grid-area: headline;
  }
  .search-button{
    grid-area: search-button;
    justify-self: center;
    width: 35%;
  }
}
</style>