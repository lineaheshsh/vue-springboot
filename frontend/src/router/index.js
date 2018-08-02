import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Sidebar from '../components/Side/Sidebar.vue'

import Login from '@/components/Login/Login'
import Crawler from '@/components/Crawler/Crawler'
import PhotoGalley from '@/components/Photo/PhotoGallery'
import UserInfo from '@/components/User/UserInfo'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      components: {
        default: Main,
        sidebar: Sidebar
      }
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/logout',
      components: {
        default: Main,
        sidebar: Sidebar
      }
    },
    {
      path: '/crawler',
      components: {
        default: Crawler,
        sidebar: Sidebar
      }
    },
    {
      path: '/photo',
      components: {
        default: PhotoGalley,
        sidebar: Sidebar
      }
    },
    {
      path: '/info',
      components: {
        default: UserInfo,
        sidebar: Sidebar
      }
    }
  ]
})
