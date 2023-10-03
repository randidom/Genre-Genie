<template>
  <div>
    <div class="Title">
      <h2> Your Favorite Movies</h2>
    </div>
    <div class="scrolling-container">
    <div class="container">
      <div class="Ultimate-grid">
        <div class="item" v-for="(movie, index) in movies" :key= "movie.id">
          <div class="content">
            <img v-bind:src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" width="200"/>
            <h3>{{movie.title}}</h3>
            <br>{{movie.overview}}
            <br />
          <h5>Release Date: {{ movie.release_date }}</h5>
          <br />
          <h5>Rating: {{ movie.vote_average }} / 10</h5>
           </div>
           <button class="remove" @click="removeFromFavorites(index)"> Remove From Favorites </button>
          </div>
          </div>
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
        movies: []
    }
  },
  methods: {
    removeFromFavorites(index){
      const userId = this.$store.state.user.id;
      const movieId = this.movies[index].id;
      if (userId !== undefined && movieId !== undefined) {
      service.deleteMovieFromFav(userId, movieId).then(
        (response) => {
          if (response.status === 200) {
          //Remove the deleted movie from the array
          //This will prevent the page from having to reload with the movie missing and will now just remove it
          //from the line up of movies
          this.movies.splice(index, 1);
        } else {
          console.error("Failed to remove movie from favorites");
        }
      }).catch((error) => {
        console.error("An error occurred", error);
      
        }
      
      )
      }
    }
  },


  created(){
   const userId = this.$store.state.user.id
          service.getAllUserFavoriteMovies(userId).then(
              (response) => {
                if (response.status === 200) {
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


.Title {
  position: absolute;
  text-align: left;
  margin: 20px;
  left: 20px;
  color: white;
  font-size: 30px;
}
.remove{
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
  display: flex;
  align-self: center;
}
</style>