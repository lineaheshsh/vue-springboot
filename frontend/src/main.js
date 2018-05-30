// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// store.js 를 불러와
import { store } from './store'

import axios from 'axios'
import VueSession from 'vue-session'

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import 'chart.js'

Vue.use(BootstrapVue)
Vue.use(VueSession)
Vue.prototype.$http = axios
Vue.prototype.$eventHub = new Vue()

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
