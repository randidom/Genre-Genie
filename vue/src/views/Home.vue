<template>
  <body class="container">
    <header>
      <!-- <div class="button-container">
        <router-link to="/login" class="button">Log In</router-link>
      </div> -->
      <div class="button-container">
        <router-link to="/register" class="button">Sign Up</router-link>
      </div>
    </header>  

    <main>
      <div class="about">
      <h1>GENRE GENIE</h1>
      <hr>
      <p class="about-text">
        Welcome to Cap Movies, your go-to movie destination! We specialize in curating personalized movie recommendations tailored to your unique taste. Whether you're a fan of action-packed blockbusters, heartwarming rom-coms, or mind-bending sci-fi, we've got the perfect movie for you. Join our community of movie lovers and never miss a great film again. Register now and embark on a cinematic journey like never before – your next favorite movie is just a click away.
      </p>
      </div>
    </main>  
  </body>
</template>

<script>
export default {
  name: "home",
};
</script>

<style>
/* .container {
  display: grid;
  grid-template-columns: 3 fr 1fr;
  grid-template-areas: 
    "header header"
    "about .";
}

header{
  grid-area: header;
  display: flex;
  justify-content: flex-end;
}

main {
  grid-area: about;
  display: flex;
  flex-direction: row;
  align-content: flex-end;
  max-width: 3fr;
  margin-left: 50px;
  bottom: 50px;
} */

.about { 
  display: grid;
  /* background: linear-gradient(135deg, #2fbaf1, #161d75);  */
  text-align: left;
  top: 45%;
  left: 10%;
  /* padding: 40px;  */
  border-radius: 10px;
  margin-left: 50px;
  /* width: 80%; */
  max-width: 550px;
  position: absolute;
  /* top: 49%;
  right: 23%; 
  top: 49%;
  left: 73%;
  transform: translate(-50%, -50%);
  /* backdrop-filter: blur(10px); */
}

.about-text {
  text-align: left; 
  /* font-size: 16px; */
  line-height: 1.5;
  margin: 0;
  /* padding: 10px; */
}

hr{
  height: 1px;
  background-color:#3DCAB1;
  opacity: 70%;
  border:none;
}

.button-container {
  display: flex;
  justify-content: flex-end;
  width: 200px;
  justify-content: space-between;
  margin-top: 20px;
}

.button {
  flex: 1;
  padding: 10px 20px;
  background-color: #071C42;
  color: #f2f2f2;
  text-align: center;
  text-decoration: none;
  border: none;
  /* border-radius: 5px; */
  cursor: pointer;
  margin-right: 10px;
  transition: background-color 0.3s ease;
}

.button:hover {
  /* background-color: #3DCAB1; */
  background-color: #BD7437;
}

</style>
