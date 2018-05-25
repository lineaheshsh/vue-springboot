<template>
  <div>
    <div class="logo"></div>
    <div class="login-block">
      <h1>Login</h1>
      <div class="input-form">
        <i class="fas fa-user fa-lg" aria-hidden="true"></i>
        <input type="text" value="" v-model="user" v-on:keyup="login" placeholder="Username" id="username" />
      </div>
      <div class="input-form">
        <i class="fas fa-key fa-lg" aria-hidden="true"></i>
        <input type="password" value="" v-model="password" placeholder="Password" id="password" />
      </div>
      <button v-on:click="login">Submit</button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      user: '',
      password: ''
    }
  },
  methods: {
    login () {
      console.log('user --> ' + this.user)
      console.log('password --> ' + this.password)

      this.$http.get('/loginCheck?ID=12345')
          .then(function (response) {
            console.log(response);
          }).catch(function (error) {
            console.log(error);
          })
    //   this.$http({
    //     method: 'post',
    //     url: '/loginCheck',
    //     data: {
    //       user: this.user
    //     }
    //   })
    }
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
