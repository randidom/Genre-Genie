<template>
<div>
  <div class="Title">
       <h1>Add Movies to Favorites</h1>
       </div>
 
  <div class="container">
     
   
<div class="Three-columns">
      <div class="column"><h3>Add favorite</h3><br> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Soluta inventore laudantium sapiente quam unde illum rem<div class="button-container"><button class="button1">Add Favorite</button></div>
       </div>
       </div>
    
      

    <div class="Three-columns1">
      <div class="column1"><h3>Add favorite</h3><br> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Soluta inventore laudantium sapiente quam unde illum rem<div class="button-container"><button class="button2">Add Favorite</button></div>
     </div>
      </div>

    <div class="Three-columns2">
      <div class="column2"><h3>Add favorite</h3><br> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Soluta inventore laudantium sapiente quam unde illum rem<div class="button-container"><button class="button3">Add Favorite</button></div>
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

.Title{
position: absolute;
text-align: left;
margin: 20px;
left: 20px;
color: white;
font-size: 30px;
}
.container {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  /* min-height: 85vh; */
  padding-bottom: 20px;
  margin: 30px;
  width: 100%;
  height: 850px;
 
}

.Three-columns {
  display: flex;
  justify-content: space-between;
  width: 85%;
  padding:10px 0;
  background-color: rgba( 22,29,117,0.5);
  height: 70%;
  align-items: flex-end;
  color: white;
}

.Three-columns1 {
  display: flex;
  justify-content: space-between;
  width: 85%;
  padding:10px 0;
  background-color:rgba(12,238,238,0.5);
  height: 70%;
  align-items: flex-end;
   color: white;
}
.button1{
  background-color: #331d80;
  width: 180px;
    color: #000;
    font-size: 12px;
    padding:12px 0;
    border: 0;
    cursor: pointer;
    border-radius: 9px;
    outline: none;
    margin-top: 30px;
}
.button2{
  background-color:rgb(248, 163, 5); 
  width: 180px;
    color: #000;
    font-size: 12px;
    padding:12px 0;
    border: 0;
    cursor: pointer;
    border-radius: 9px;
    outline: none;
    margin-top: 30px;
}
.button-container{
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 30px;
  height: 100%;
}
.button3{
background-color: #331d80;
  width: 180px;
    color: #000;
    font-size: 12px;
    padding:12px 0;
    border: 0;
    border-radius: 9px;
    outline: none;
    cursor: pointer;
    margin-top: 30px;
     transition: background-color 0.3s ease;
}
.button1:hover {
  background-color: #0056b3;
}
.button2:hover {
  background-color: rgb(247, 194, 97); ;
}
.button3:hover {
  background-color: #0056b3;
}

.Three-columns2 {
  display: flex;
  justify-content: space-between;
  width: 89%;
  padding:10px 0;
  background-color: rgba( 22,29,117,0.5);
  height: 70%;
  align-items: flex-end;
  color: white;
}

.column {
  padding: 20px;
  
  text-align: center;
  min-height: 180px; 
}

.column1 {
  padding: 20px;
 
  text-align: center;
  min-height: 180px; 
}
.column2 {
  padding: 20px;
  
  text-align: center;
  min-height: 180px; 
}

</style>