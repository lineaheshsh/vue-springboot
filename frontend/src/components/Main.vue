<template>
  <div id="contents">
    <b-container class="bv-example-row chartDiv">
      <div v-if="loginCount > -1">
        <h1>changho.lee은 오늘 {{loginCount}}번 로그인 하셨습니다.</h1>
      </div>

      <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
      <b-row class="chart-container">
          <b-col cols="6">
            <h3>Line Chart</h3>
            <line-chart></line-chart>
          </b-col>
          <b-col cols="6">
            <h3>Access Times this week</h3>
            <bar-chart :chart-data="barDatacollection" :styles="barStyle"></bar-chart>
          </b-col>
      </b-row>

      <!-- Columns are always 50% wide, on mobile and desktop -->
      <b-row class="chart-container">
          <b-col cols="6">
            <h3>Disk Usage</h3>
            <pie-chart :chart-data="pieDatacollection" :styles="pieStyle"></pie-chart>
          </b-col>
          <b-col cols="6">
            <h3>Scatter Chart</h3>
            <scatter-chart></scatter-chart>
          </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import lineChart from './Chart/LineChart.vue'
import barChart from './Chart/BarChart.vue'
import pieChart from './Chart/PieChart.vue'
import scatterChart from './Chart/ScatterChart.vue'

export default {
  name: 'Main',
  data () {
    return {
      pieDatacollection: null,
      barDatacollection: null,
      loginCount: -1
    }
  },
  mounted () {
    this.showDisk()
    // this.showMonth()
    // this.getTodayLoginCount()
    this.accessCheck()
    // this.temp()
  },
  methods: {
    showDisk () {
      this.$http.get('/showDisk')
        .then((result) => {
          console.log('result : ' + result)

          if (result.data) {
            let totalSpace = result.data.TotalSpace
            let usableSpace = result.data.UsableSpace
            let freeSpace = totalSpace - usableSpace

            this.pieDatacollection = {
              labels: ['Free', 'Usage'],
              datasets: [
                {
                  label: 'Data One',
                  backgroundColor: ['#17a2b8', 'beige'],
                  data: [freeSpace, usableSpace]
                }
              ]
            }
          }
        })
    },
    getTodayLoginCount () {
      this.$http.get('/getTodayLoginCount', {
        params: {
          seq: this.$store.state.seq
        }
      }).then((result) => {
        console.log('result : ' + result)
        console.log()
        this.loginCount = result.data.cnt
      })
    },
    showMonth () {
      this.barDatacollection = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: ['#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige'],
            data: [10, 80, 56, 60, 6, 45, 15]
          }
        ]
      }
    },
    accessCheck () {
      if (this.$store.state.seq) {
        this.$http.post('/accessCountAdd', {
          data: {
            seq: this.$store.state.seq
          }
        }).then((result) => {
          console.log('result : ' + result)
          let dataArr = []

          let data = result.data

          dataArr.push(0)
          dataArr.push(0)
          dataArr.push(0)
          dataArr.push(0)
          dataArr.push(0)
          dataArr.push(data[0].cnt)
          dataArr.push(data[1].cnt)

          this.barDatacollection = {
            labels: ['2018-07-14', '2018-07-15', '2018-07-16', '2018-07-17', '2018-07-18', '2018-07-19', '2018-07-20'],
            datasets: [
              {
                label: 'Count',
                backgroundColor: ['#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige'],
                data: dataArr
              }
            ]
          }
        })
      }
    }
  },
  computed: {
    pieStyle () {
      return {
        height: `450px`,
        width: `450px`,
        position: `relative`
      }
    },
    barStyle () {
      return {
        height: `450px`,
        width: `450px`,
        position: `relative`
      }
    }
  },
  components: {
    lineChart: lineChart,
    barChart: barChart,
    pieChart: pieChart,
    scatterChart: scatterChart
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

#contents {
  width: 80%;
  margin-top: 30px;
  position: relative;
  left: 20%;
}

.chart-container {
  position: relative;
  margin: auto;
  height: 80vh;
  width: 80vw;
}

.searchInput {
  margin-bottom: 30px;
}

.chartColumns {
  height: 500px !important;
}

.chartDiv {
  float: left;
  margin-left: 20px;
}

#bar-chart {
  height: 395px;
}

</style>
