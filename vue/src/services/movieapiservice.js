import axios from 'axios';

export default {

// getTrending(){
//     return axios.get('https://api.themoviedb.org/3/trending/all/day?api_key=ea5f97c7d5398eac0c2b48ed86ab30dc')
// }, 
getAllMovies(){
    return axios.get('/movies')
},
// This is by genre id (not genre name)
getMoviesByGenre(genre){
return axios.get(`/movies/genre/${genre}`)
},
getMoviesByTitle(title){
return axios.get(`/movies/titles/${title}`)
},
//This is a movie object in the parameters coming from our own database 
createAFavoriteMovie(movie){
return axios.post('/favorites', movie)
 },
getAllUserFavoriteMovies(){
return axios.get('/favorites/movies')
},
getGenreList(){
   return axios.get('/movies/genre/list')
 },
getAUserProfile(){
return axios.get('/profile/genres')
},
//This is a profile object in the parameters coming from our own database
 createNewProfile(profile) {
return axios.post('/create', profile)
   },
}
