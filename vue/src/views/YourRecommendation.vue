<template>
  <div>
    <div class="userfirstpage">
    <div class="Title">
      <h1> Movie Recommendations</h1>
    </div>
    <div class="scrolling-container">
    <div class="container">
      <div class="Ultimate-grid">
        <div class="item" v-for="movie in movies" :key= "movie.id">
          <div class="content">
            <img v-bind:src="'https://image.tmdb.org/t/p/w500/' + movie.poster_path" alt="movie poster" width="200"/>
            <h3>{{movie.title}}</h3>
            <br>{{movie.overview}}
          </div>
          <div class="button-container">
            <button class="button1" style="margin-top: 10px;" @click="addToFavorites(movie)">Add Favorite</button>
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
    }
  },
  methods: {
    recomendations() {
      service.getAllMovies().then(response => {
        this.movies = response.data
      })
    },
    addToFavorites(movie) {
      this.selectedMovies.push(movie);
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
 
 overflow-y: auto;

}


.Ultimate-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 60px;
  max-width: 100vw;
  padding: 20px;
  
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
  position: absolute;
  text-align: left;
  margin: 20px;
  left: 20px;
  color: white;
  font-size: 30px;
}

</style>







