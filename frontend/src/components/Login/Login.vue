<template>
  <div id="login">
    <div class="logo"></div>
    <div class="login-block">
      <h1>Login</h1>
      <div class="input-form">
        <i class="fas fa-user fa-lg" aria-hidden="true"></i>
        <input type="text" value="" v-model="user" placeholder="Username" id="username" />
      </div>
      <div class="input-form">
        <i class="fas fa-key fa-lg" aria-hidden="true"></i>
        <input type="password" value="" v-model="password" @keyup.enter="login" placeholder="Password" id="password" />
      </div>
      <button v-on:click="login">Submit</button>
    </div>
    <modal v-if="showModal" @close="showModal = false">
      <h3 slot="header" v-if="loginStatus">
        Success
      </h3>
      <h3 slot="header" v-else>
        Warning
      </h3>
      <span slot="body" v-if="loginStatus">
          로그인 성공!
      </span>
      <span slot="body" v-else>
          로그인 실패하였습니다. ID 또는 Password를 확인하시기 바랍니다.
      </span>
      <span slot="footer" @click="goMainPage()" v-if="loginStatus">
        <button>Ok</button>
      </span>
      <span slot="footer" @click="showModal = false" v-else>
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
      user: '',
      password: '',
      showModal: '',
      loginStatus: false
    }
  },
  methods: {
    login () {
      this.$Progress.start()
      this.$http.post('/loginCheck', {
        data: {
          user: this.user,
          password: this.password
        }
      }).then((result) => {
        if (result.data === 'failed') {
          this.$Progress.fail()
          this.loginStatus = false
          this.showModal = true
        } else {
          if (result.status === 200 && 'token' in result.data) {
            this.$Progress.finish()

            this.$session.start()
            this.$session.set('jwt', result.data.token)
            this.$session.set('user', result.data.member.m_name)
            this.$session.set('id', result.data.member.m_id)
            this.$session.set('email', result.data.member.m_email)
            this.$session.set('seq', result.data.member.m_seq)
            this.$session.set('birthday', result.data.member.m_birthday)
            this.$session.set('gender', result.data.member.m_gender)
            this.$session.set('nation', result.data.member.m_nation)

            this.loginStatus = true
            this.showModal = true
            this.$store.state.user = result.data.member.m_name
            this.$store.state.id = result.data.member.m_id
            this.$store.state.email = result.data.member.m_email
            this.$store.state.seq = result.data.member.m_seq
            this.$store.state.birthday = result.data.member.m_birthday
            this.$store.state.gender = result.data.member.m_gender
            this.$store.state.nation = result.data.member.m_nation
          }
        }
      })
    },
    goMainPage () {
      this.showModal = false
      this.$router.push('/')
    }
  },
  components: {
    Modal: Modal
  }
}
</script>

<style>
.logo {
    width: 213px;
    height: 36px;
    /* background: url('http://i.imgur.com/fd8Lcso.png') no-repeat; */
    margin: 30px auto;
}

#login {
    margin: 0 auto
}

.input-form {
    margin-top: 25px;
}

.login-block {
    width: 320px;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    border-top: 5px solid #17a2b8;
    margin: 0 auto;
}

.login-block h1 {
    text-align: center;
    color: #000;
    font-size: 18px;
    text-transform: uppercase;
    margin-top: 0;
    margin-bottom: 20px;
}

.login-block input {
    width: 80%;
    height: 42px;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    font-size: 14px;
    font-family: Montserrat;
    padding: 0 20px 0 50px;
    outline: none;
    float: right;
}

.login-block input#username {
    /* background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px top no-repeat; */
    background-size: 16px 80px;
}

.login-block input#username:focus {
    /* background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px bottom no-repeat; */
    background-size: 16px 80px;
}

.login-block input#password {
    /* background: #fff url('http://i.imgur.com/Qf83FTt.png') 20px top no-repeat; */
    background-size: 16px 80px;
}

.login-block input#password:focus {
    /* background: #fff url('http://i.imgur.com/Qf83FTt.png') 20px bottom no-repeat; */
    background-size: 16px 80px;
}

.login-block input:active, .login-block input:focus {
    border: 1px solid #ff656c;
}

.login-block button {
    width: 100%;
    height: 40px;
    background: #17a2b8;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #17a2b8;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
    font-size: 14px;
    font-family: Montserrat;
    outline: none;
    cursor: pointer;
}

.login-block button:hover {
    background: #ff7b81;
}

i {
    color: #17a2b8;
    width: 15%;
    line-height: inherit;
    height: 42px;
    padding: 15px;
    font-weight: none;
}
</style>
