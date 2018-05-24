<template>
  <div id="app">
    <Header></Header>
    <SideBar></SideBar>
    <router-view/>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/Header/Header.vue'

export default {
  name: 'App',
  components: {
    Header: Header
  }
}
</script>

<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif;}

body {margin: 0;}

.container {
  margin-right: 0px !important;
  margin-left: 0px !important;
  padding-left: 0px !important;
  padding-right: 0px !important;
}

@media (min-width: 1200px) {
  .container {
    max-width: 1540px !important;
  }
}
</style>
