<template>
  <div>
      <b-nav-form class="userSearch">
        <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Search" />
        <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
        <span class="buttonSpan">
          <a href="/user/add"><i class="fas fa-plus" style="cursor: pointer;"/></a>
          <a><i class="fas fa-minus" style="cursor: pointer;"/></a>
        </span>
      </b-nav-form>
    <b-table style="font-size: 0.9rem;" striped hover :items="uItems" :fields="fields"></b-table>
  </div>
</template>

<script>
import Modal from '../Common/Modal.vue'
import { eventBus } from '../../main'
export default {
  data () {
    return {
      fields: [ 'NUM', 'ID', 'Name', 'EMAIL', 'Gender', 'Birthday', 'Nation', 'RegDate', 'UpdateDate' ],
      uItems: [],
      showModal: false
    }
  },
  created () {
    this.setNavigation()
  },
  mounted () {
    this.getUserList()
  },
  computed: {
  },
  methods: {
    getUserList () {
      this.$http.get('/getUserList')
        .then((result) => {
          console.log('getUserList')
          let length = result.data.length
          let data = result.data
          let num = 1
          for (let i = 0; i < length; i++) {
            let _gender = ''
            if (data[i].m_gender === 'm') _gender = '남'
            else _gender = '여'

            if (data[i].m_id !== 'guest') {
              this.uItems.push({
                NUM: num++,
                ID: data[i].m_id,
                Name: data[i].m_name,
                EMAIL: data[i].m_email,
                Gender: _gender,
                Birthday: data[i].m_birthday,
                Nation: data[i].m_nation,
                RegDate: data[i].m_regdate,
                UpdateDate: data[i].m_update
              })
            }
          }
        })
    },
    showUser () {
      console.log('showUser')
      this.showModal = true
    },
    setNavigation () {
      eventBus.$emit('initNavigation', 'List')
    }
  },
  components: {
    Modal: Modal
  }
}
</script>

<style>

.table {
  margin-left: 60px;
  margin-top: 20px;
  width: 90% !important;
}

.userSearch {
  width: 90%;
  margin-left: 60px;
  margin-bottom: 20px;
  margin-top: 20px;
}

.userSpan {
  float: right;
  display: inline-flex;
  width: 100px;
}

</style>
