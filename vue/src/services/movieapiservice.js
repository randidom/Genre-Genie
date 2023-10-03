import axios from 'axios';

export default {

getAllMovies(){
    return axios.get('/movies')
},
// This is by genre id (not genre name)
getMoviesByGenre(genre){
return axios.post('/movies/genre/recs', genre)
},
getMoviesByTitle(title){
return axios.get(`/movies/titles/${title}`)
},
//This is a movie object in the parameters coming from our own database 
createAFavoriteMovie(movie){
return axios.post('/favorites', movie)
 },
 //This will directly link up to the current user login and get their selected favorite movies that was created in the database
getAllUserFavoriteMovies(userId){
return axios.get(`/favorites/movies/${userId}`)
},
getGenrePreferences(userId){
return axios.get(`/profile/genres/${userId}`)
},
//This is a genre object in the parameters coming from our own database
 createNewProfile(genre) {
return axios.post('/create', genre)
   },
   //This will remove a user's favorite movie from the database by passing the user id attached with the movie id
deleteMovieFromFav(userId, movieId){
   return axios.delete(`/favorites/${userId}/${movieId}`)
   }
}
