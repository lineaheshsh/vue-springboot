<template>
  <div id="contents">
    <h3><em>[ User List ]</em></h3>
    <b-table striped hover :items="uItems" :fields="fields"></b-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      fields: [ 'first_name', 'last_name', 'show_details' ],
      uItems: [
        { isActive: true, age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
        { isActive: false, age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        { isActive: false, age: 89, first_name: 'Geneva', last_name: 'Wilson', _showDetails: true },
        { isActive: true, age: 38, first_name: 'Jami', last_name: 'Carney' }
      ]
    }
  },
  mounted () {
  },
  computed: {
  },
  methods: {
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
