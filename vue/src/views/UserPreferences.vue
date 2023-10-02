<template>
<div>
    <div class="select">
      <div
        v-for="item in items"
        :key="item.id"
        :class="{ 'select__item': true, 'select__item--selected': isSelected(item) }"
        @click="toggleItem(item)"
      >
        <span class="select__text">{{ item.name }}</span>
      </div>
    </div>
    <div class="title">
      <hr>
      <h2> Select Up to 3 <br>
       Genres</h2>
      <div v-show="selectedItems.length > 3" class="error-message">
      You can only select up to 3 genres.
    </div>
       
      </div>

    <button class="submit-button" @click="createUserProfiles">Submit</button>
  </div>
</template>

<script>
import service from '../services/movieapiservice.js'
export default {
  name: "preferences",
  data() {
    return {
      errorMessage: "",
      items: [
        //This connects to following genre_ids exactly how they are in the external API
      { name: "Action", id: 28 },
      { name: "Adventure", id: 12 },
      {name: "Animation", id: 16},
      {name: "Comedy", id: 35},
      {name: "Crime", id: 80},
      {name: "Documentary", id: 99},
      {name: "Drama", id: 18},
      {name: "Family", id: 10751},
      {name: "Fantasy", id: 14},
      {name: "History", id: 36},
      {name: "Horror", id: 27},
      {name: "Music", id: 10402},
      {name: "Mystery", id: 9648},
      {name: "Romance", id: 10749},
      {name: "Science Fiction", id: 878},
      {name: "TV Movie", id: 10770},
      {name: "Thriller", id: 53},
      {name: "War", id: 10752},
      {name: "Western", id: 37},
      {name: "None", id: null}
      ],
      selectedItems: [],
      name: '',
    };
  },
  methods: {
    toggleItem(item) {
     // Find the index of the selected item in selectedItems based on item's ID
    const index = this.selectedItems.findIndex(selectedItem => selectedItem.id === item.id);
    if (index !== -1) {
      // Item is already selected, so deselect it
      this.selectedItems.splice(index, 1);
    } else if (this.selectedItems.length < 3) {
      // Item is not selected and the limit is not reached, so select it
      this.selectedItems.push(item);
    } else {
      this.errorMessage = "You can only select up to 3 genres.";
    }
  },
  createUserProfiles(){
    if (this.selectedItems.length > 3) {
    this.errorMessage = "You can only select up to 3 genres.";
    return;
  }
  //The method below requires a genre object 
  const genre = {
    userId: this.$store.state.user.id,
    genre_id: this.selectedItems.map(item => item.id) // Map selected items to genre IDs
  }
    service.createNewProfile(genre).then(response => {
    this.errorMessage = "";
    this.selectedItems = response.data;
    this.$router.push('/recommendation');
    })
  },
  isSelected(item) {
      // Check if an item is selected based on its id
      return this.selectedItems.some(selectedItem => selectedItem.id === item.id);
    },
  },
}




</script>

<style>
.select {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  max-width: 300px;
  gap: 1px;
  margin-left:920px;
  margin-top: 0px;
  width: 100%; 
}

.select__item:nth-child(even) {
  background: #071C42;
}

.select__item {
  width: 200px;
  height: 200px;
  padding: 10px;
  cursor: pointer;
  text-align: center;
  background: #3DCAB1;
  transition: background 0.1s;
  opacity: 0.7;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20pt;
  color: #f2f2f2;
}

.select__item--selected {
  background: #BD7437;
  opacity: 0.7;
}

.select__item--selected:nth-child(even) {
  background: #BD7437;
  opacity: 0.7;
}

.title {
  position: absolute;
  top: 140px;
  left: 5%;
  font-size: 20pt;
}

.submit-button {
  width: 10%;
  padding: 10px;
  background-color: #3DCAB1;
  color: #f2f2f2;
  border: none;
  cursor: pointer;
  font-size: 30px;
  margin-top: 20px;
  opacity: 0.9;
  position: absolute;
  right: 400px;
  
}

/* .submit-button:hover {
  background-color: #131a46;
} */

.name{
  color: white;
}

.name{
  width: 50%;
}
/* prevents horizontal scroll */
html, body {
    max-width: 100%;
    overflow-x: hidden;
}

</style>