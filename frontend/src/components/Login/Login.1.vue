<template>
  <b-jumbotron header="Login Form">
    <b-input-group>
      <b-input-group-prepend>
        <i class="fas fa-user fa-lg" aria-hidden="true"></i>
      </b-input-group-prepend>
      <b-form-input type="text" v-model="user" required placeholder="UserName"></b-form-input>
    </b-input-group>
    <b-input-group>
      <b-input-group-prepend>
        <i class="fas fa-key fa-lg" aria-hidden="true"></i>
      </b-input-group-prepend>
      <b-form-input type="password" v-model="password" required placeholder="Password"></b-form-input>
    </b-input-group>
    <b-btn variant="primary" href="#">Login</b-btn>
  </b-jumbotron>
</template>

<script>
export default {
  data () {
    return {
      user: '',
      password: ''
    }
  }
}
</script>

<style>
  .jumbotron {
    width: 500px !important;
    margin: 0 auto !important;
  }

  .btn {
    float: right;
    margin-top: 20px;
  }

  i {
    width: 50px;
    line-height: inherit !important;
  }

  .input-group {
      margin-top: 20px
  }

  .form-control {
    height: 30px;
  }
</style>
