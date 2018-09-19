<template>
  <div>
    <b-card bg-variant="light" id="userProfile">
        <b-form-group horizontal
                      label="Name:"
                      label-class="text-sm-right"
                      label-for="userName">
          <input v-validate="'required'" name="user" type="text" v-model="user">
          <span class="message">{{ errors.first('user') }}</span>
        </b-form-group>
        <b-form-group horizontal
                      label="ID:"
                      label-class="text-sm-right"
                      label-for="userID"
                      :invalid-feedback="invalidId">
          <input v-validate="'required|alpha'" name="regex" type="text" v-model="id">
          <span class="message">{{ errors.first('regex') }}</span>
        </b-form-group>
        <b-form-group horizontal
                      label="E-mail:"
                      label-class="text-sm-right"
                      label-for="userEmail"
                      :invalid-feedback="invalidEmail">
          <input v-validate="'required|email'" name="email" type="text" v-model="email">
          <span class="message">{{ errors.first('email') }}</span>
        </b-form-group>
        <b-form-group horizontal
                      label="BirthDay:"
                      label-class="text-sm-right"
                      label-for="nestedCountry">
          <input v-validate="'required|numeric'" name="birthday" type="text" v-model="birthday">
          <span class="message">{{ errors.first('birthday') }}</span>
        </b-form-group>
        <b-form-group horizontal
                      label="Nation:"
                      label-class="text-sm-right"
                      label-for="nestedCountry">
          <input v-validate="'required'" name="nation" type="text" v-model="nation" style="width: 50%;">
          <b-form-select v-model="nation" :options="nations" class="mb-3" style="width: 50%;">
            <template slot="first">
              <option :value="null" disabled>-- Please select an option --</option>
            </template>
          </b-form-select>
          <span class="message">{{ errors.first('nation') }}</span>
        </b-form-group>
        <b-form-group horizontal
                      label="Gender:"
                      label-class="text-sm-right"
                      label-for="nestedCountry">
          <b-form-checkbox-group id="checkboxes1" name="flavour1" v-model="gender" :options="genders" style="margin-top: 10px;">
          </b-form-checkbox-group>
        </b-form-group>
        <div class="form-button">
          <b-button v-on:click="save">Save</b-button>
        </div>
    </b-card>
    <modal v-if="showModal" @close="showModal = false">
      <h3 slot="header" v-if="addStatus">
        Success
      </h3>
      <h3 slot="header" v-else>
        Warning
      </h3>
      <span slot="body" v-if="addStatus">
          사용자 등록 성공!
      </span>
      <span slot="body" v-else>
          사용자 등록에 실패하였습니다.
      </span>
      <span slot="footer" @click="showModal = false">
        <button>Ok</button>
      </span>
    </modal>
  </div>
</template>

<script>
import Modal from '../Common/Modal.vue'
import { eventBus } from '../../main'

export default {
  data () {
    return {
      nations: [
        {text: 'South Korea', value: 'South Korea'},
        {text: 'USA', value: 'USA'},
        {text: 'JAPAN', value: 'JAPAN'}
      ], // Must be an array reference!
      genders: [
        {text: 'MALE', value: 'm'},
        {text: 'FEMAIL', value: 'f'}
      ],
      nation: '',
      user: '',
      birthday: '',
      id: '',
      email: '',
      gender: '',
      showModal: false,
      addStatus: ''
    }
  },
  created () {
    this.setNavigation()
  },
  methods: {
    save () {
      let result = this.validateBeforeSubmit()
      if (result) {
        let data = JSON.stringify({
          m_name: this.user,
          m_id: this.id,
          m_email: this.email,
          m_birthday: this.birthday,
          m_gender: this.gender[0],
          m_nation: this.nation
        })
        let config = {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        }
        this.$Progress.start()
        this.$http.post('/user', data, config
        ).then((result) => {
          if (result.data === 'failed') {
            this.$Progress.fail()
            this.addStatus = result.data
            this.showModal = true
          } else {
            if (result.status === 200) {
              this.$Progress.finish()
              this.addStatus = result.data
              this.showModal = true
              this.initData()
            }
          }
        })
      }
    },
    initData () {
      this.user = ''
      this.id = ''
      this.email = ''
      this.birthday = ''
      this.gender = ''
      this.nation = ''
    },
    setNavigation () {
      eventBus.$emit('initNavigation', 'Add')
    },
    validateBeforeSubmit () {
      this.$validator.validateAll().then((result) => {
        if (result) {
          // eslint-disable-next-line
          alert('Form Submitted!')
          return true
        } else {
          alert('Correct them errors!')
          return false
        }
      })
    }
  },
  components: {
    Modal: Modal
  }
}
</script>

<style>
input {
  display: block;
  width: 100%;
  padding: .375rem .75rem;
  font-size: 1rem;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}

span.message {
  color: red;
}
</style>
