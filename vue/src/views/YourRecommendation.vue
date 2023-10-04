<template>
  <div>
    <div class="Title">
      <h1>Movie Recommendations</h1>
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
            <button class="button1" style="margin-top: 10px;" @click="addToFavorites(index)" :class="{ 'remove-favorite': selectedMovies.includes(movie.id) }">
            {{ selectedMovies.includes(movie.id) ? 'Remove from Favorites' : 'Add to Favorites' }}
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
      selectedMovies: [], // New data property to store selected movies
      genre: {}

    }
  },
  methods: {
    recommendations() {
  const userId = this.$store.state.user.id;
  const recommendedMovies = []; //Empty array to store recommended movies

  // This grabs the genre preferences by the user id
  service.getGenrePreferences(userId).then(response => {
    this.genre = response.data;

    // This grabs the movies based on genre preferences, matching the genre id's associated with that user id
    service.getMoviesByGenre(this.genre).then(response => {
      const genreMovies = response.data;

      // Calculate a matching score for each movie
      // Example if a user picks action + comedy which a movie has, it will show that movie first
      // Before a movie that just has action or comedy
      genreMovies.forEach(movie => {
        movie.matchScore = this.calculateMatchScore(movie);

        //Check if the movie is not already recommended before adding it to the list
        //This will prevent duplicate movies from showing up since one movie can have multiple genre id's
        //that the user selects
        if (!recommendedMovies.some(recommendedMovie => recommendedMovie.id === movie.id)) {
          recommendedMovies.push(movie);
        }
      });
      // This will sort the movies by matchScore in descending order, so the most top matching movie(s) appear first
      recommendedMovies.sort((a, b) => b.matchScore - a.matchScore);

      this.movies = recommendedMovies;
    });
  });
},
calculateMatchScore(movie) {
    //Calculate the matching score based on how many genre IDs match the user's preferences
    //This method will allow movie recommendations to go in hierachiral order (most matched) to most recommened from least recommened
    const userGenreIds = this.genre.genreIds;
    const movieGenreIds = movie.genre_ids;

    let matchScore = 0;
    userGenreIds.forEach(userGenreId => {
      if (movieGenreIds.includes(userGenreId)) {
        matchScore++;
      }
    });

    return matchScore;
  },
    addToFavorites(index) {
if (this.movies.length === 0) {
    //Handle the case where there are no movies to add as favorites.
    return;
  }

  const movie = this.movies[index];
  const movieId = movie.id;

  if (this.selectedMovies.includes(movieId)) {
    //If the movie is in favorites, remove it
    const movieIndex = this.selectedMovies.indexOf(movieId);
    this.selectedMovies.splice(movieIndex, 1);

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
    this.selectedMovies.push(movieId);

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
},
  
 created() {
    this.recommendations();
  
  },
  name: "AddFavorite",
};
</script>

<style scoped>

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  margin: 30px;
  width: 100%;
 
}
  
.scrolling-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow-y: auto;
  overflow-x: hidden;
}

.Ultimate-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 60px;
  max-width: 100%;
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
  background-color: rgb(248, 163, 5);
  width: auto;
  white-space: nowrap;
  color: #000;
  font-size: 12px;
  padding: 10px 30px;
  border: 0;
  cursor: pointer;
  border-radius: 0;
  outline: none;
  margin-left: 55%;
}
.button1:hover {
  background-color: rgb(223, 190, 102);

}

.Title {
  position: sticky;
  text-align: left;
  margin: 20px;
  left: 20px;
  color: white;
  font-size: 30px;
}

.button1.remove-favorite {
  background-color: rgb(18, 18, 49);
  color: #fff;
}
 @media screen and (max-width: 768px) {
  .container {
    flex-direction: column;
    align-items: center;
    margin: 10px;
  }
  .Ultimate-grid {
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
    max-width: 90vw;
    padding: 10px;
  }
  .Title {
    font-size: 5px;
    text-align: center;
    margin: 5px;
    left: 0;
  }
  .button1 {
    width: 140px;
    font-size: 10px;
    padding: 8px 0;
    margin-top: 8px;
  }
  .content img {
    width: 150px;
  }
} 
    

</style>







