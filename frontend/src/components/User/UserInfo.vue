<template>
  <div id="contents">
    <b-card bg-variant="light">
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
          <b-form-input id="nestedStreet" disabled v-model="this.$store.state.user"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="ID:"
                      label-class="text-sm-right"
                      label-for="nestedCity">
          <b-form-input id="nestedCity" disabled v-model="this.$store.state.id"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="E-mail:"
                      label-class="text-sm-right"
                      label-for="nestedState">
          <b-form-input id="nestedState" disabled v-model="this.$store.state.email"></b-form-input>
        </b-form-group>
        <b-form-group horizontal
                      label="BirthDay:"
                      label-class="text-sm-right"
                      label-for="nestedCountry">
          <b-form-input id="nestedCountry" disabled v-model="this.$store.state.birthday"></b-form-input>
        </b-form-group>
        <b-button>Edit</b-button>
        <b-button>Save</b-button>
      </b-form-group>
    </b-card>
  </div>
</template>

<script>

export default {
  data () {
    return {
    }
  },
  methods: {
  },
  components: {
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

.form-gruop {
    padding-top: 10px;
    padding-bottom: 10px;
}

button {
    float: right;
}
</style>
