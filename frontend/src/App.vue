<template>
  <div id="app">
    <Header></Header>
    <section>
      <router-view name="sidebar"></router-view>
      <router-view/>
    </section>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/Header/Header.vue'

export default {
  name: 'App',
  data () {
    return {
      user: '',
      password: '',
      seq: ''
    }
  },
  components: {
    Header: Header
  }
}
</script>

<style scoped>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif;}

body {margin: 0;}

section {
  display: flex;
  width: 100%;
  flex: 0 0 280px;
  border-top: 4px solid white;
  position: absolute;
  top: 56px;
  left: 0px;
  height: 100%;
}
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
