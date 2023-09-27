<template>
  <div class="about">
    <h1></h1>
    <p class="about-text">
      Welcome to Cap Movies, your go-to movie destination! We specialize in curating personalized movie recommendations tailored to your unique taste. Whether you're a fan of action-packed blockbusters, heartwarming rom-coms, or mind-bending sci-fi, we've got the perfect movie for you. Join our community of movie lovers and never miss a great film again. Register now and embark on a cinematic journey like never before – your next favorite movie is just a click away.
    </p>

     <div class="button-container">
      <router-link to="/login" class="button">Log In</router-link>
    </div>
    <div class="button-container">
      <router-link to="/register" class="button">Sign Up</router-link>
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
  top: 49%;
  left: 23%;
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
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.button {
  flex: 1;
  padding: 10px 20px;
  background-color: #331d80;
  color: white;
  text-decoration: none;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 10px;
  transition: background-color 0.3s ease;
}

.button:hover {
  background-color: #0056b3;
}
</style>
