<template>
  <div id="sidebar">
    <div id="profile" v-show="this.$store.state.user">
      <img src="../../assets/avatar-1.f743093.svg" id="avatar" class="inline-block">
      <div id="user-name">
        <span class="text-white">{{ this.$store.state.user }}</span>
        <hr>
      </div>
      <div id="user-actions">
        <p>1990.06.24</p>
        <p>MAN</p>
        <p>Korean</p>
      </div>
    </div>
    <div id="noProfile" v-show="!this.$store.state.user" />
    <b-nav vertical class="w-100">
      <b-nav-item> <img src="https://png.icons8.com/office/40/000000/person-male.png"> Info</b-nav-item>
      <b-nav-item> <img src="https://png.icons8.com/ultraviolet/40/000000/paste.png"> Career</b-nav-item>
      <b-nav-item href="crawler"> <img src="https://png.icons8.com/dotty/50/000000/under-computer.png"> Crawling</b-nav-item>
      <b-nav-item> <img src="https://png.icons8.com/color/50/000000/combo-chart.png"> Chart</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
export default {
  name: 'Sidebar',
  data () {
    return {
      user: ''
    }
  },
  created () {
    console.log('user ; ' + this.$store.state.user)
  },
  methods: {
    login (user) {
      console.log('[Sidebar-login] this user : ' + this.user)
      console.log('[Sidebar-login] user : ' + user)
      this.user = user
    }
  }
}
</script>

<style scoped>
#sidebar {
    width: 20% !important;
    box-shadow: 0 10px 20px rgba(0,0,0,.19), 0 6px 6px rgba(0,0,0,.23);
    position: fixed;
    float: left;
    height: 100%;
}

#profile {
    height: auto;
    background-color: #17a2b8 !important;
}

#noProfile {
    border-top: 10px solid #17a2b8;
    margin-bottom: 20px
}
.nav-link {
    padding: 0px;
    margin-bottom: 20px;
}

.nav-item a {
    margin-left: 85px;
    margin-top: 20px;
}

img {
    padding: 20px;
}

a > img {
    width: 25px;
    height: 25px;
    padding: 0px;
}

#user-name {
    left: 90px;
    bottom: 77px;
    position: relative;
    width: 159px;
}

#user-actions {
    left: 90px;
    bottom: 71px;
    position: relative;
    width: 171px;
}

p {
    color: beige;
}

a {
    color: rgba(0,0,0,.54);
}
</style>
