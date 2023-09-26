<template>
  <div>
    <div v-for='movie in movies' :key='movie.id'>
      {{movie.title}}
    </div>
  </div>
</template>

<script>
import Service from '../services/movieapiservice.js'
export default {
data() {
return {
  movies: []
}

},
created(){
  Service.getTrending().then(response => {
    this.movies = response.data.results
  })
}
}
</script>

<style>

</style>