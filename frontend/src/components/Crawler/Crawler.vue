<template>
  <div id="contents">
    <b-container class="bv-example-row chartDiv">

      <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
      <b-row class="chart-container">
          <b-col cols="12">
            <bar-chart :chart-data="cBarDatacollection" :styles="barStyle"></bar-chart>
          </b-col>
      </b-row>

      <!-- Stack the columns on mobile by making one full-width and the other half-width -->
      <b-row>
          <b-col cols="12" md="10" class="searchInput">
            <b-input-group>
              <b-form-input placeholder="Search Keyword" v-model="kwd"></b-form-input>
              <b-input-group-append>
                <b-btn variant="outline-success" v-on:click="search">Search</b-btn>
              </b-input-group-append>
            </b-input-group>
          </b-col>
      </b-row>

      <b-row>
        <b-col cols="10">
          <b-table striped hover :items="cItems"></b-table>
          <b-pagination align="right" size="md" :total-rows="100" v-model="currentPage" :per-page="10">
          </b-pagination>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import barChart from '../Chart/BarChart.vue'

export default {
  name: 'Main',
  data () {
    return {
      cBarDatacollection: null,
      cItems: null,
      kwd: '',
      currentPage: 1,
      totalPage: 0
    }
  },
  mounted () {
    this.getCrawlingData()
    this.search()
  },
  methods: {
    getCrawlingData () {
      this.$http.get('/getCrawlingData')
        .then((result) => {
          let results = result.data.result
          let isData = results.length
          if (isData > 0) {
            let barLabel = []
            let barData = []

            for (let i = 0; i < isData; i++) {
              barLabel.push(results[i].writer)
              barData.push(results[i].count)
            }

            this.cBarDatacollection = {
              labels: barLabel,
              datasets: [
                {
                  label: 'Data One',
                  backgroundColor: ['#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige', '#17a2b8', 'beige'],
                  data: barData
                }
              ]
            }
          }
        })
    },
    search () {
      console.log()
      this.$http.post('/search', {
        data: {
          kwd: this.kwd,
          currentPage: this.currentPage,
          pageName: 'naverComment'
        }
      }).then((result) => {
        let results = result.data.result.result
        let isData = results.length
        for (let i = 0; i < isData; i++) {
          this.cItems = results
          console.log('push : ' + results)
        }

        this.currentPage = result.data.paging.pageNo
        this.totalPage = result.data.paging.finalPageNo
      })
    }
  },
  computed: {
    barStyle () {
      return {
        height: `450px`,
        width: `750px`,
        position: `relative`
      }
    }
  },
  components: {
    barChart: barChart
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
  width: 100%;
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
