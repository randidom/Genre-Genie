<template>
  <div id="app">
    <div id="nav" v-if="$store.state.token != ''">
      <h2>Welcome {{ this.$store.state.user.username}}!</h2>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
       <router-link v-bind:to="{ name: 'recommendation' }" v-if="$store.state.token != ''">Recommended</router-link>
      <router-link v-bind:to="{ name: 'search' }" v-if="$store.state.token != ''">Search</router-link>
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
h2{
  color: white;
}

</style>