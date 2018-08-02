<template>
  <div id="sidebar">
    <div id="profile" v-show="this.$store.state.user">
      <img v-if="this.$store.state.gender == 'm'" src="../../assets/man.jpg" id="avatar" class="inline-block">
      <img v-else src="../../assets/woman.jpg" id="avatar" class="inline-block">
      <div id="user-name">
        <span class="text-white">{{ this.$store.state.user }}</span>
        <hr>
      </div>
      <div id="user-actions">
        <p>{{ this.$store.state.birthday }}</p>
        <p v-if="this.$store.state.gender == 'm'">MAN</p>
        <p v-else>WOMAN</p>
        <p>{{ this.$store.state.nation }}</p>
      </div>
    </div>
    <div id="noProfile" v-show="!this.$store.state.user" />
    <b-nav vertical class="w-100">
      <b-nav-item href="info"> <img src="https://png.icons8.com/office/40/000000/person-male.png"> Info</b-nav-item>
      <b-nav-item href="photo"> <img src="https://png.icons8.com/ultraviolet/40/000000/paste.png"> Photo</b-nav-item>
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
    if (this.$session.exists()) {
      this.$store.state.birthday = this.$session.get('birthday')
      this.$store.state.email = this.$session.get('email')
      this.$store.state.id = this.$session.get('id')
      this.$store.state.gender = this.$session.get('gender')
      this.$store.state.nation = this.$session.get('nation')
    }
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

#profile img {
  width: 100px;
  height: 100px;
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
