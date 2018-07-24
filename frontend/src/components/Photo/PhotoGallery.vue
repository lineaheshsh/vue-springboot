<template>
  <div id="contents">
    <b-row class="chart-container">
        <b-col cols="12">
        <div v-masonry transition-duration="0.3s" item-selector=".item" :origin-top="true" :horizontal-order="false">
            <div class="row">
                <div v-masonry-tile class="col-md-4" v-for="(image) in images" v-bind:key="image">
                <div class="card m-4" style="width: 18rem;">
                <img class="card-img-top" src="../../assets/images/7.jpg" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><strong>{{ image }}</strong></h5>
                    <p class="card-text">{{ image }}</p>
                    </div>
                </div>
                </div>
            </div>
        </div>
        </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      images: ''
    }
  },
  created () {
    this.images = ['../../assets/images/7.jpg', '../../assets/images/KakaoTalk_20180404_171441026.jpg', '../../assets/images/KakaoTalk_20180404_171441026.jpg', '../../assets/images/KakaoTalk_20180404_171441026.jpg']
  }
}
</script>

<style>
#contents {
  width: 100%;
  margin-top: 30px;
  position: relative;
  left: 20%;
  display: flex;
}
</style>
