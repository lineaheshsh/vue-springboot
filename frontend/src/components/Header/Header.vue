<template>
    <b-container class="header">
        <b-navbar toggleable="md" type="dark" variant="info">
            <router-link to="/" exact>
                <b-navbar-brand href="#"><img src="../../assets/logo.png" style="width: 20px; height: 20px"></b-navbar-brand>
            </router-link>

            <b-collapse is-nav id="nav_collapse">
                <!-- <b-navbar-nav v-for="menu in menus" :key="menu">
                  <b-nav-item href="#">{{ menu }}</b-nav-item>
                </b-navbar-nav> -->

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">

                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Search" v-model="keyword" v-on:keyup.enter="addTodo"/>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit" v-on:click="search">Search</b-button>
                    </b-nav-form>

                    <b-nav-item-dropdown text="Lang" right>
                        <b-dropdown-item href="#"  v-for="lang in langs" :key="lang">{{ lang }}</b-dropdown-item>
                    </b-nav-item-dropdown>

                    <div v-if="this.$store.state.user">
                      <b-nav-item-dropdown right>
                        <!-- Using button-content slot -->
                          <template slot="button-content">
                          <em>{{ this.$store.state.user }}</em>
                          </template>
                        <b-dropdown-item href="#">Profile</b-dropdown-item>
                        <b-dropdown-item href="/logout">Signout</b-dropdown-item>
                      </b-nav-item-dropdown>
                    </div>
                    <div v-else>
                      <b-nav-item-dropdown right>
                      <!-- Using button-content slot -->
                        <template slot="button-content">
                        <em>User</em>
                        </template>
                        <b-dropdown-item href="#">Profile</b-dropdown-item>
                        <b-dropdown-item href="/login">Login</b-dropdown-item>
                      </b-nav-item-dropdown>
                    </div>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </b-container>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      menus: [],
      langs: [],
      keyword: '',
      user: ''
    }
  },
  created () {
    this.menus = ['News', 'Chart', 'Project']
    this.langs = ['EN', 'CN', 'KO']

    this.$store.state.user = localStorage.getItem('user')
    // this.$eventHub.$on('loginEventBus', this.login)
  },
  methods: {
    logout () {
      this.$store.state.user = ''
      localStorage.removeItem('user')
      this.$router.push('/')
      console.log('logout success....')
    },
    search () {
      console.log('search Kwd : ' + this.keyword)
    }
  }
}
</script>

<style scoped>
.header {
    position: fixed;
    z-index: 1000;
}
</style>
