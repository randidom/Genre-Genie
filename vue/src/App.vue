<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{name: 'login'}" v-else>Login</router-link>
       <router-link v-bind:to="{ name: 'Recommendation' }" v-if="$store.state.token != ''">Recommendation</router-link>
      <router-link v-bind:to="{ name: 'search' }" v-if="$store.state.token != ''">Search</router-link>
       <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&nbsp;
    </div>
    <router-view />
  </div>
</template>
<style>

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

#nav {
  overflow: hidden;
  background-color: rgb(40, 44, 63);
}

#nav a {
  float: right;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

#nav a:hover {
  background-color: #ddd;
  color: black;
}


</style>