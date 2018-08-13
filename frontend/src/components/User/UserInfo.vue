<template>
  <div id="contents">
    <b-card bg-variant="light" id="userProfile">
      <b-form-group horizontal
                    breakpoint="lg"
                    label="User Profile"
                    label-size="lg"
                    label-class="font-weight-bold pt-0"
                    class="mb-0">
        <b-form-group horizontal
                      label="Name:"
                      label-class="text-sm-right"
                      label-for="nestedStreet">
          <b-form-input id="nestedStreet" :disabled="isDisable" v-model="user"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="ID:"
                      label-class="text-sm-right"
                      label-for="nestedCity"
                      :invalid-feedback="invalidId">
          <b-form-input id="nestedCity" :disabled="isDisable" v-model="id"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="E-mail:"
                      label-class="text-sm-right"
                      label-for="nestedState"
                      :invalid-feedback="invalidEmail">
          <b-form-input id="nestedState" :disabled="isDisable" v-model="email"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="BirthDay:"
                      label-class="text-sm-right"
                      label-for="nestedCountry">
          <b-form-input id="nestedCountry" :disabled="isDisable" v-model="birthday"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="Nation:"
                      label-class="text-sm-right"
                      label-for="nestedCountry">
          <b-form-input id="nestedCountry" :disabled="isDisable" v-model="nation"></b-form-input>
        </b-form-group>
        <div class="form-button">
          <b-button v-on:click="isDisabled">Edit</b-button>
          <b-button v-on:click="save">Save</b-button>
        </div>
      </b-form-group>
    </b-card>
    <modal v-if="showModal" @close="showModal = false">
      <h3 slot="header" v-if="loginStatus">
        Success
      </h3>
      <h3 slot="header" v-else>
        Warning
      </h3>
      <span slot="body" v-if="status">
          회원정보 수정 성공!
      </span>
      <span slot="body" v-else>
          회원정보 수정에 실패하였습니다.
      </span>
      <span slot="footer" @click="showModal = false">
        <button>Ok</button>
      </span>
    </modal>
  </div>
</template>

<script>
import Modal from '../Common/Modal.vue'

export default {
  data () {
    return {
      isDisable: true,
      user: '',
      id: '',
      email: '',
      birthday: '',
      nation: '',
      showModal: '',
      status: ''
    }
  },
  mounted () {
    this.init()
  },
  computed: {
    invalidId () {
      if (this.id.length > 8) {
        return ''
      } else if (this.id.length > 0) {
        return 'Enter at least 4 characters'
      } else {
        return 'Please enter something'
      }
    },
    invalidEmail () {
      if (this.email.length === '') {
        return ''
      } else if (this.email.length > 0) {
        return 'Enter at least 4 characters'
      } else {
        return 'Please enter something'
      }
    }
  },
  methods: {
    init () {
      this.user = this.$store.state.user
      this.id = this.$store.state.id
      this.email = this.$store.state.email
      this.birthday = this.$store.state.birthday
      this.nation = this.$store.state.nation
    },
    save () {
      this.$Progress.start()
      this.$http.post('/userUpdate', {
        data: {
          seq: this.$store.state.seq,
          user: this.user,
          id: this.id,
          email: this.email,
          birthday: this.birthday,
          nation: this.nation
        }
      }).then((result) => {
        this.$Progress.finish()
        if (result.data === 'success') {
          this.status = true
          this.showModal = true
        } else {
          this.status = false
          this.showModal = true
        }
      })
    },
    isDisabled () {
      this.isDisable = !this.isDisable
    }
  },
  components: {
    Modal: Modal
  }
}
</script>

<style>
#contents {
  width: 80%;
  margin-top: 30px;
  position: relative;
  left: 20%;
  display: flex;
}

.card {
    margin: 0 auto;
    width: 80%;
}

#userProfile {
    height: 600px;
}

.form-gruop {
    padding-top: 10px;
    padding-bottom: 10px;
    margin-bottom: 3rem !important;
}

.form-button {
    float: right;
}

button {
    margin-left: 10px;
}

</style>
