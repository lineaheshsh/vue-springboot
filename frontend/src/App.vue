<template>
  <div id="app">
    <Header></Header>
    <section>
      <router-view name="sidebar"></router-view>
      <router-view/>
      <vue-progress-bar></vue-progress-bar>
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
      id: '',
      email: '',
      password: '',
      seq: '',
      birthday: '',
      gender: '',
      nation: '',
      axios_config: {
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }
      }
    }
  },
  mounted () {
    //  [App.vue specific] When App.vue is finish loading finish the progress bar
    this.$Progress.finish()
  },
  created () {
    //  [App.vue specific] When App.vue is first loaded start the progress bar
    this.$Progress.start()
    //  hook the progress bar to start before we move router-view
    this.$router.beforeEach((to, from, next) => {
      //  does the page we want to go to have a meta.progress object
      if (to.meta.progress !== undefined) {
        let meta = to.meta.progress
        // parse meta tags
        this.$Progress.parseMeta(meta)
      }
      //  start the progress bar
      this.$Progress.start()
      //  continue to next page
      next()
    })
    //  hook the progress bar to finish after we've finished moving router-view
    this.$router.afterEach((to, from) => {
      //  finish the progress bar
      this.$Progress.finish()
    })
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
