import axios from 'axios';

export default {

getAllMovies(){
    return axios.get('/movies')
},
// This is by genre id (not genre name)
getMoviesByGenre(genre){
return axios.get('/movies/genre/recs', genre)
},
getMoviesByTitle(title){
return axios.get(`/movies/titles/${title}`)
},
//This is a movie object in the parameters coming from our own database 
createAFavoriteMovie(movie){
return axios.post('/favorites', movie)
 },
getAllUserFavoriteMovies(userId){
return axios.get(`/favorites/movies/${userId}`)
},
getGenreList(){
   return axios.get('/movies/genre/list')
 },
getGenrePreferences(userId){
return axios.get(`/profile/genres/${userId}`)
},
//This is a genre object in the parameters coming from our own database
 createNewProfile(genre) {
return axios.post('/create', genre)
   },
}
