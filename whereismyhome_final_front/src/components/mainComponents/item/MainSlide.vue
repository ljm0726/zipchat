<template>
  <div class="carousel">
    <h3>{{ this.favoriteList[0].dong }}</h3>
    <b-carousel
      v-if="this.favoriteList"
      id="carousel-1"
      v-model="slide"
      :interval="2000"
      background="#ababab"
      img-width="200"
      img-height="120"
      style="text-shadow: 1px 1px 2px #000"
    >
      <b-carousel-slide
        caption="."
        img-blank
        img-blank-color="#43434A"
        v-for="(favoriteItem, index) in this.favoriteList"
        :key="index"
      >
        <div>{{ favoriteItem.apartmentName }}</div>
        {{ favoriteItem.area }} m<sup>2</sup> &nbsp; {{ favoriteItem.floor }} 층
        <div>{{ parseInt(favoriteItem.dealamount.replace(",", "")) | price }} 만원</div>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
import { mapfavoriteListState, mapActions, mapMutations } from "vuex";
import http from "@/api/http.js";
const memberStore = "memberStore";

export default {
  data() {
    return {
      slide: 0,
      sliding: null,
      favoriteList: [],
    };
  },
  props: {
    mainDong: String,
  },
  computed: {
  },
  created() {
    http
      .get(`/apt/mainFavoriteList/${this.mainDong.dongCode}`)
      .then(({ data }) => {
        // console.log(data);
        if (data != null) {
          this.favoriteList = data;
          // console.log("tf", this.favoriteList[2]);
        }
      })
      .catch((error) => {
        console.log("UserRegisterVue: error : ");
        console.log(error);

      });
  },
  watch: {},
  methods: {
 
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
  },


  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>
<style scoped>
.carousel {
  margin-top: 15%;
  width: 300px;
}

.carousel h3 {
  margin-top: 20px;
}
</style>
