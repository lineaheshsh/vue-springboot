<template>
  <div id="contents">
    <b-container class="bv-example-row chartDiv">
      <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
      <b-row class="chart-container-small">
          <b-col cols="12" class="chart-status">
            <div class="chart-form">
              <div class="chart-frame">
                <div class="">
                  <div class="chart-header" id="green">
                    <img src="https://png.icons8.com/office/50/000000/cd.png">
                  </div>
                  <div class="chart-content">
                    <p class="category">디스크 사용량</p>
                    <h3 class="title">{{ freeSpace }}/{{ totalSpace }}GB</h3>
                  </div>
                  <div class="chart-footer">
                    <div class="stats" v-if="(freeSpace/totalSpace)*100 > 90">
                      <i class="fas fa-exclamation-circle"></i>
                      Get More Space...
                    </div>
                    <div class="stats" v-else>
                      <i class="far fa-thumbs-up"></i>
                      Disk is sufficient!
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="chart-form">
              <div class="chart-frame">
                <div class="">
                  <div class="chart-header" id="palegoldenrod">
                    <img src="https://png.icons8.com/office/50/000000/home.png">
                  </div>
                  <div class="chart-content">
                    <p class="category">오늘 총 방문 횟수</p>
                    <h3 class="title">{{ loginCount }}</h3>
                  </div>
                  <div class="chart-footer">
                    <div class="stats">
                      <i class="fas fa-calendar-alt"></i>
                      Last 24 Hours
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </b-col>
      </b-row>

      <!-- Columns are always 50% wide, on mobile and desktop -->
      <b-row class="chart-container">
          <b-col cols="6">
            <h3>"{{ this.$store.state.user }}"님의 이번주 방문횟수</h3>
            <bar-chart :chart-data="barDatacollection" :styles="barStyle"></bar-chart>
          </b-col>
          <b-col cols="6">
            <h3>"{{ this.$store.state.user }}"님의 현재 위치</h3>
            <vue-daum-map
              :appKey="appKey"
              :center.sync="center"
              :level.sync="level"
              :mapTypeId="mapTypeId"
              :libraries="libraries"
              @load="onLoad"
              @zoom_changed="onMapEvent('zoom_changed', $event)"
              @dragend="onMapEvent('drag', $event)"
              @maptypeid_changed="onMapEvent('maptypeid_changed', $event)"
              style="width:500px;height:400px;">
            </vue-daum-map>
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
import VueDaumMap from 'vue-daum-map'

export default {
  name: 'Main',
  data () {
    return {
      pieDatacollection: null,
      barDatacollection: null,
      loginCount: -1,
      totalSpace: null,
      freeSpace: null,
      appKey: '6e20f7c4e0062d5285c487e9d3b18cd7', // 테스트용 appkey
      center: {lat: 0, lng: 0}, // 지도의 중심 좌표
      level: 3, // 지도의 레벨(확대, 축소 정도),
      mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
      libraries: [], // 추가로 불러올 라이브러리
      map: null, // 지도 객체. 지도가 로드되면 할당됨.
      marker: {position: this.center}
    }
  },
  mounted () {
    this.showDisk()
    // this.showMonth()
    this.getTotalTodayLoginCount()
    this.accessCheck()
  },
  methods: {
    showDisk () {
      this.$http.get('/showDisk')
        .then((result) => {
          console.log('result : ' + result)

          if (result.data) {
            this.totalSpace = result.data.TotalSpace
            let usableSpace = result.data.UsableSpace
            this.freeSpace = this.totalSpace - usableSpace

            // this.pieDatacollection = {
            //   labels: ['Free', 'Usage'],
            //   datasets: [
            //     {
            //       label: 'Data One',
            //       backgroundColor: ['#17a2b8', 'beige'],
            //       data: [freeSpace, usableSpace]
            //     }
            //   ]
            // }
          }
        })
    },
    getTotalTodayLoginCount () {
      this.$http.get('/getTotalTodayLoginCount', {
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
                backgroundColor: ['#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8'],
                data: dataArr
              }
            ]
          }
        })
      }
    },
    onLoad (map) {
      this.$getLocation({
        enableHighAccuracy: false,
        timeout: Infinity,
        maximumAge: 0
      })
        .then(coordinates => {
          console.log(coordinates)
          console.log()
          this.center.lat = coordinates.lat
          this.center.lng = coordinates.lng
          console.log(map)
          this.map = map
        })
    },
    onMapEvent (eventName, event) {
      console.log('eventName : ' + eventName)
      console.log('event : ' + event)
      console.log()
      this.$getLocation({
        enableHighAccuracy: false,
        timeout: Infinity,
        maximumAge: 0
      })
        .then(coordinates => {
          console.log(coordinates)
          console.log()
          this.center.lat = coordinates.lat
          this.center.lng = coordinates.lng
        })
      console.log('lat : ' + this.center.lat)
      console.log('lng : ' + this.center.lng)
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
    scatterChart: scatterChart,
    VueDaumMap
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
  font-family: Roboto,Helvetica,Arial,sans-serif;
  font-weight: 300;
  line-height: 1.5em;
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

.chart-container-small{
  position: relative;
  margin: auto;
  height: 30vh;
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

.chart-status {
  display: inline-flex;
}

.chart-form {
  width: 300px;
  height: 200px;
  padding-right: 15px;
  padding-left: 15px;
}

.chart-frame {
  background-color: seashell;
  border-radius: 5px;
  display: inline-block;
  width: 250px;
  position: relative;
  margin: 25px 0;
  overflow: unset;
  -webkit-box-shadow: 0 1px 4px 0 rgba(0,0,0,.14);
  box-shadow: 0 1px 4px 0 rgba(0,0,0,.14);
  border-radius: 3px;
  color: rgba(0,0,0,.87);
}

.col-6 {
  background-color: seashell;
  border-radius: 5px;
  position: relative;
  margin: 25px 0;
  margin-right: 20px;
  overflow: unset;
  -webkit-box-shadow: 0 1px 4px 0 rgba(0,0,0,.14);
  box-shadow: 0 1px 4px 0 rgba(0,0,0,.14);
  border-radius: 3px;
  color: rgba(0,0,0,.87);
  max-width: 45% !important;
}

.chart-header {
  width: 86px;
  height: 86px;
  margin: -20px 15px 0;
  border-radius: 3px;
  padding: 15px;
  float: left;
  text-align: center;
}

.chart-header {
    float: left;
}

div#green {
  background: linear-gradient(60deg,#66bb6a,#43a047);
}

div#palegoldenrod {
  background-color: palegoldenrod;
}

.chart-content {
    text-align: right;
    padding-top: 10px;
}

.chart-content {
    padding: 15px 20px;
}

.chart-footer {
    margin: 0 20px 10px;
    padding: 10px 0 0;
    border-top: 1px solid #eee;
}

.chart-header img {
    font-size: 36px!important;
    line-height: 56px;
    width: 56px;
    height: 56px;
    color: #fff!important;
}

.category:not([class*=text-]), h3 {
    color: #999;
}

p {
    font-size: 14px;
    margin: 0 0 10px;
}

.chart-content .title {
    margin: 0;
    font-size: 1.25rem !important;
}
</style>
