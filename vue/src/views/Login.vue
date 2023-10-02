<template>

  <div id="login">
    <hr>
    <form @submit.prevent="login">
      <h3 >Have An Account? Please Sign In</h3>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text"  id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
   
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/recommendation");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style media="screen">
      *,
*:before,
*:after{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    background-image: url("../assets/movie-background-collage.jpg");
    background-size: 2500px 2000px;
}
.background{
    width: 430px;
    height: 520px;
    position: absolute;
    transform: translate(-50%,-50%);
    left: 50%;
    top: 50%;
}
.background {
    height: 200px;
    width: 200px;
    position: absolute;
    border-radius: 50%;
}

form{
    height: 520px;
    width: 400px;
    /* background-color: rgba(255,255,255,0.13); */
    position: absolute;
    transform: translate(-50%,-50%);
    top: 40%;
    left: 75%;
    border-radius: 10px;
    /* backdrop-filter: blur(10px); */
    /* border: 2px solid rgba(255,255,255,0.1); */
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;
}
form *{
    font-family: 'Poppins',sans-serif;
    color: #ffffff;
    letter-spacing: 0.5px;
    outline: none;
    border: none;
}
form h3{
    font-size: 32px;
    font-weight: 500;
    line-height: 42px;
    text-align: center;
}

label{
    display: block;
    margin-top: 30px;
    font-size: 16px;
    font-weight: 500;
}
input{
    /* display: block; */
    height: 50px;
    width: 100%;
    /* background-color: rgba(255,255,255,0.07); */
    /* border-radius: 3px; */
    /* padding: 0 10px; */
    margin-top: 8px;
    font-size: 14px;
    font-weight: 400;
    background: transparent;
    box-sizing: border-box;
    border: 5px solid;
    border-color: #3DCAB1;
    opacity: 70%;
}

button{
    margin-top: 20px;
    width: 100%;
    background-color: #3DCAB1;
    color: #f2f2f2;
    padding: 15px 0;
    font-size: 18px;
    font-weight: 600;
    /* border-radius: 5px; */
    cursor: pointer;
    /* opacity: 70%; */
}

p{
  position: relative;
top:20px;
}

</style>