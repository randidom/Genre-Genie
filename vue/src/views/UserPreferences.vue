<template>
<div>
    <div class="select">
      <div
        v-for="item in items"
        :key="item"
        :class="{ 'select__item': true, 'select__item--selected': selectedItems.includes(item) }"
        @click="toggleItem(item)"
      >
        <span class="select__text">{{ item }}</span>
      </div>
    </div>

    <div class="title">
      <hr>
      <h2> Select Your Three <br>
            Favorite Genres</h2>
       
      </div>

    <button class="submit-button" @click="redirectToLoginPage">Submit</button>
  </div>
</template>

<script>
export default {
  name: "preferences",
  data() {
    return {
      items: [
        "Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary",
        "Drama", "Family", "Fantasy", "History", "Horror", "Music", "Mystery",
        "Romance", "Science Fiction", "TV Movie", "Thriller", "War", "Western", "Christmas"
      ],
      selectedItems: []
    };
  },
  methods: {
    toggleItem(item) {
      if (this.selectedItems.includes(item)) {
        this.selectedItems = this.selectedItems.filter(i => i !== item);
      } else {
        this.selectedItems.push(item);
      }
    },
     redirectToLoginPage() {
      this.$router.push('/login');
    }
  }
};

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

</style>