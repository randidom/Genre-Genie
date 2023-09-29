<template>
  <div>
    <div class="Title">
      <h1> Movie Recommendations</h1>
    </div>
    <div class="container">
      <div class="Ultimate-grid">
        <div class="item">
          <div class="content">
            <h3>Add favorite</h3>
            <br> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Soluta inventore laudantium sapiente quam unde illum rem
          </div>
          <div class="button-container">
            <button class="button1">Add Favorite</button>
          </div>
        </div>
        <div class="item">
          <div class="content">
            <h3>Add favorite</h3>
            <br> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Soluta inventore laudantium sapiente quam unde illum rem
          </div>
          <div class="button-container">
            <button class="button2">Add Favorite</button>
          </div>
        </div>
        <div class="item">
          <div class="content">
            <h3>Add favorite</h3>
            <br> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Soluta inventore laudantium sapiente quam unde illum rem
          </div>
          <div class="button-container">
            <button class="button3">Add Favorite</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddFavorite",
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding-bottom: 20px;
  margin: 30px;
  width: 100%;
  height: 850px;
}
.Ultimate-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 60px;
  max-width: 4000px;
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
  margin-top: 20px;
}
.button1 {
  background-color: #331D80;
  width: 180px;
  color: #000;
  font-size: 12px;
  padding: 12px 0;
  border: 0;
  cursor: pointer;
  border-radius: 9px;
  outline: none;
}
.button2 {
  background-color: rgb(248, 163, 5);
  width: 180px;
  color: #000;
  font-size: 12px;
  padding: 12px 0;
  border: 0;
  cursor: pointer;
  border-radius: 9px;
  outline: none;
}
.button3 {
  background-color: #331D80; 
  width: 180px;
  color: #000;
  font-size: 12px;
  padding: 12px 0;
  border: 0;
  border-radius: 9px;
  outline: none;
  cursor: pointer;
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