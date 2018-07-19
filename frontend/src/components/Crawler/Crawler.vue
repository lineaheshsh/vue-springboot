<template>
  <div id="contents">
    <b-container class="bv-example-row chartDiv">
      <h3><em>2018-07-19 기준 "맨체스터 유나이티드" 관련 뉴스 댓글 정보</em></h3>

      <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
      <b-row class="chart-container">
          <b-col cols="12">
            <h4>[ Naver Comment Ranking Top 10 ]</h4>
            <bar-chart :chart-data="cBarDatacollection" :styles="barStyle"></bar-chart>
          </b-col>
      </b-row>
      <hr>
      <b-row class="chart-container">
          <b-col cols="12">
            <h4>[ Hot Issue Keyword ]</h4>
            <wordcloud :data="cWordCloud" nameKey="issue_word" valueKey="count">
            </wordcloud>
          </b-col>
      </b-row>
      <hr>
      <b-row>
        <b-col cols="10">
          <h4>[ Comments collected ]</h4>
          <b-input-group>
              <b-form-input placeholder="Search Keyword" v-model="kwd"></b-form-input>
              <b-input-group-append>
                <b-btn variant="outline-success" v-on:click="search">Search</b-btn>
              </b-input-group-append>
            </b-input-group>
          <b-table striped hover :items="cItems"></b-table>
          <b-pagination align="right" size="md" :total-rows="totalRow" v-model="currentPage" v-on:input="goPage(currentPage)" limit="10" :per-page="10">
          </b-pagination>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import barChart from '../Chart/BarChart.vue'
import wordcloud from 'vue-wordcloud'

export default {
  name: 'Main',
  data () {
    return {
      cBarDatacollection: null,
      cWordCloud: [],
      cItems: null,
      kwd: '',
      currentPage: 1,
      totalRow: 0
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
          let rankingResults = result.data.ranking.result
          let isData = rankingResults.length
          if (isData > 0) {
            let barLabel = []
            let barData = []

            for (let i = 0; i < isData; i++) {
              barLabel.push(rankingResults[i].writer)
              barData.push(rankingResults[i].count)
            }

            this.cBarDatacollection = {
              labels: barLabel,
              datasets: [
                {
                  label: ['green'],
                  backgroundColor: ['#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8', '#17a2b8'],
                  data: barData
                }
              ]
            }
          }

          let wordCloudResults = result.data.wordCloud.result
          isData = wordCloudResults.length
          if (isData) {
            this.cWordCloud = wordCloudResults
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
        }

        this.currentPage = result.data.paging.pageNo
        this.totalRow = result.data.paging.totalCount
      })
    },
    goPage (page) {
      console.log()
      this.$http.post('/search', {
        data: {
          kwd: this.kwd,
          currentPage: page,
          pageName: 'naverComment'
        }
      }).then((result) => {
        let results = result.data.result.result
        let isData = results.length
        for (let i = 0; i < isData; i++) {
          this.cItems = results
        }

        this.currentPage = result.data.paging.pageNo
        this.totalRow = result.data.paging.totalCount
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
    barChart: barChart,
    wordcloud
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3, h4 {
  font-weight: normal;
  margin-bottom: 20px;
}
h4 {
  background-color: beige;
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
  display: flex;
}

.chart-container {
  position: relative;
  margin: auto;
  height: 70vh;
  width: 70vw;
}

.searchInput {
  margin-bottom: 30px;
}

.chartColumns {
  height: 500px !important;
}

.chartDiv {
  float: left;
  margin-left: 100px;
}

#bar-chart {
  height: 395px;
}

.wordCloud {
  width: 70% !important;
}
</style>
