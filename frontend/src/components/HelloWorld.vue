<template>
  <div class="hello">
    <line-chart></line-chart>
  </div>
</template>

<script>
import lineChart from './Chart/BarChart.vue'
export default {
  name: 'HelloWorld',
  data () {
    return {
      dataentry: null,
      datalabel: null,
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      dataset: [65, 59, 80, 81, 56, 55, 40]
    }
  },
  components: {
    lineChart: lineChart
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
