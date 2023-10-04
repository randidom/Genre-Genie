<template>
  <div id="register" class="text-center">
    <hr>
    <form class="form" @submit.prevent="register">
      <hr>
      <h2 id="create-account">Create Account</h2>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">CREATE ACCOUNT</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success'},
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  margin-top: 10px;
}
label {
  margin-right: 0.5rem;
  margin-top: -2%;
}
button {
  margin-top: 3px;
  text-align: left;
  padding-left: 20px;
}
h2 {
  position: relative;
  bottom:10px;
}

hr {
  width: 600px;  
  margin-bottom: 50px;
}
@media (max-width: 768px) {
  #register {
    padding: 10px; 
  }
  h2 {
    font-size: 28px;
    }
  .form-input-group {
    margin-bottom: 0.5rem;
    grid-area: input;
  width: 60%;
  }
  label {
    margin-right: 0.2rem; 
  }
  button {
    margin-top: 5px; 
    padding-left: 10px;
      grid-area: search-button;
    justify-self: center;
    width: 60%;
  }
  div[role="alert"] {
    font-size: 14px; 
  }
  p{
    font-size: 14px;
  }

  
}

</style>
