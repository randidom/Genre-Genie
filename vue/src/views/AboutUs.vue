<template>
  <div class="about">
    <h1></h1>
    <p class="about-text">
      Welcome to Cap Movies, your go-to movie destination! We specialize in curating personalized movie recommendations tailored to your unique taste. Whether you're a fan of action-packed blockbusters, heartwarming rom-coms, or mind-bending sci-fi, we've got the perfect movie for you. Join our community of movie lovers and never miss a great film again. Register now and embark on a cinematic journey like never before – your next favorite movie is just a click away.
    </p>
     <div class="button-container">
      <router-link v-bind:to="{ name: 'login' }">Log In</router-link>&nbsp;|&nbsp;
       <router-link v-bind:to="{ name: 'register' }">Register</router-link>&nbsp;|&nbsp;
    </div>
  </div>
   
</template>

<script>
export default {
  name: "about",
};
</script>

<style>
.about {
  background: linear-gradient(135deg, #2fbaf1, #161d75); 
  text-align: center;
  padding: 40px; 
  border-radius: 10px;
  margin: 0 auto;
  width: 80%;
  max-width: 800px;
  position: absolute;
  top: 46%;
  left: 26%;
  transform: translate(-50%, -50%);
  backdrop-filter: blur(10px);
}
.about-text {
  color: rgb(188, 211, 230);
  text-align: left; 
  font-size: 16px;
  line-height: 1.5;
  margin: 0;
  padding: 10px;
}

.button-container {
  margin-top: 20px;
}

.button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #0073e6;
  color: white;
  text-decoration: none;
  border: none;
  border-radius: 5px;
  margin-right: 10px;
  cursor: pointer;
}

.button:hover {
  background-color: #0056b3;
}
</style>