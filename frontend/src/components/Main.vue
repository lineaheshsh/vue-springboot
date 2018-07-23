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
            <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-25">
              <div class="md-card md-card-stats md-theme-default">
                <div class="md-card-header">
                  <img src="https://png.icons8.com/color/50/000000/cd.png">store
                </div>
                <div class="md-card-content">
                  <p class="category">Revenue</p>
                  <h3 class="title">$34,245</h3>
                </div>
                <div class="md-card-actions md-alignment-left">
                  <div class="stats">
                    <i class="md-icon md-icon-font md-theme-default">date_range</i>
                    Last 24 Hours
                  </div>
                </div>
              </div>
            </div>
            <h3>Line Chart</h3>
            <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-25">
              <div class="md-card md-card-stats md-theme-default">
                <div class="md-card-header">
                  <img src="https://png.icons8.com/color/50/000000/cd.png">store
                </div>
                <div class="md-card-content">
                  <p class="category">Revenue</p>
                  <h3 class="title">$34,245</h3>
                </div>
                <div class="md-card-actions md-alignment-left">
                  <div class="stats">
                    <i class="md-icon md-icon-font md-theme-default">date_range</i>
                    Last 24 Hours
                  </div>
                </div>
              </div>
            </div>            
          </b-col>
      </b-row>

      <!-- Columns are always 50% wide, on mobile and desktop -->
      <b-row class="chart-container">
          <b-col cols="6">
            <h3>Disk Usage</h3>
            <pie-chart :chart-data="pieDatacollection" :styles="pieStyle"></pie-chart>
          </b-col>
          <b-col cols="6">
            <h3>Access Times this week</h3>
            <bar-chart :chart-data="barDatacollection" :styles="barStyle"></bar-chart>
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
          let regDateArr = []
          let data = result.data

          for (let i = 0; i < data.length; i++) {
            dataArr.push(data[i].cnt)
            regDateArr.push(data[i].reg_date)
          }

          this.barDatacollection = {
            labels: regDateArr,
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

.md-card {
    display: inline-block;
    position: relative;
    width: 100%;
    margin: 25px 0;
    overflow: unset;
    -webkit-box-shadow: 0 1px 4px 0 rgba(0,0,0,.14);
    box-shadow: 0 1px 4px 0 rgba(0,0,0,.14);
    border-radius: 3px;
    color: rgba(0,0,0,.87);
    background: #fff;
}

.md-layout-item.md-size-25 {
    min-width: 25%;
    max-width: 25%;
    flex: 0 1 25%;
}

.md-layout-item {
    padding-right: 15px;
    padding-left: 15px;
}

.md-card-header {
    background-color: navajowhite;
}

.md-card-stats .md-card-header {
    float: left;
    text-align: center;
}

.md-card-stats .md-card-content {
    text-align: right;
    padding-top: 10px;
}

.md-card .md-card-content {
    padding: 15px 20px;
}

.md-card .md-card-actions {
    margin: 0 20px 10px;
    padding: 10px 0 0;
    border-top: 1px solid #eee;
}

.md-card-stats .md-card-header img {
    font-size: 36px!important;
    line-height: 56px;
    width: 56px;
    height: 56px;
    color: #fff!important;
}

.md-card .category:not([class*=text-]) {
    color: #999;
}

p {
    font-size: 14px;
    margin: 0 0 10px;
}

.md-card-stats .title {
    margin: 0;
}
</style>
