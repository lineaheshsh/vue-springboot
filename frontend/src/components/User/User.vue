<template>
  <div id="contents">
    <h3><em> User > {{ navigation }}</em></h3>
    <router-view></router-view>
  </div>
</template>

<script>
import { eventBus } from '../../main'

export default {
  data () {
    return {
      navigation: ''
    }
  },
  created () {
    eventBus.$on('initNavigation', this.initNavigation)
  },
  methods: {
    initNavigation (navi) {
      this.navigation = navi
    }
  }
}
</script>

<style scoped>
#contents {
  width: 80%;
  margin-top: 30px;
  position: relative;
  left: 20%;
  display: inline-block !important;
}

h3 {
  margin-left: 20px;
}
</style>
