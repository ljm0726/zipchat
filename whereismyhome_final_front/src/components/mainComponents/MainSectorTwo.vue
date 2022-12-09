<template>
  <div class="home">
    <div class="page-section" id="portfolio">
      <div class="container justify-content-center align-items-center">
        <div class="sectionTitle">
          <h1 class="section-heading text-uppercase">Place Info</h1>
        </div>
        <div v-if="isLogin">

          <div
            class="row row-cols-1 row-cols-md-3 g-4 justify-content-center mt-5"
            v-if="(favoriteDongList.length != 0) || (favoriteDongList==null) "
          >
            <main-slide
              class="card"
              style="height: 300px"
              v-for="(favoriteItem, index) in favoriteDongList.slice(0, 3)"
              :key="index"
              :mainDong="favoriteItem"
            >
            </main-slide>
          </div>
          <div
            class="row row-cols-1 row-cols-md-3 g-4 justify-content-center"
            v-else
          >
            <div class="plzLogin">관심 지역을 등록해주세요!</div>
          </div>
        </div>
        <div v-else class="d-flex justify-content-center">
          <div class="plzLogin">로그인 후 이용하실 수 있는 서비스입니다.</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import MainSlide from "@/components/mainComponents/item/MainSlide.vue";

const memberStore = "memberStore";
const favoriteStore = "favoriteStore";
export default {
  name: "MainSectorTwo",

  data() {
    return {
      dongName: null,
      hereLogin: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(favoriteStore, ["favoriteDongList", "dongLoc", "favoriteDong"]),
  },
  created() {
    if (this.isLogin) {
      this.getFavoriteDongList(this.userInfo.id);
    }
    // console.log("main isLogin List ", this.favoriteDongList);
    // console.log("isLogin:" + this.isLogin);
  },
  watch: {
    isLogin: function () {
      if (this.isLogin) this.getFavoriteDongList(this.userInfo.id);
      // console.log("main isLogin List2 ", this.favoriteDongList);
    },
  },

  methods: {
    ...mapActions(favoriteStore, ["getDongLoc", "getFavoriteDongList"]),
  },
  components: {
    MainSlide,
  },
};
</script>

<style scoped>
.home {
  height: 95vh;
  background: linear-gradient(#ffffff, rgb(224, 224, 224));
}
#portfolio {
  height: 100%;
  display: flex;
  align-content: center;
}
.sectionTitle h1 {
  text-align: center;
  font-size: 30px;
  font-weight: 300;
  color: #222;
  letter-spacing: 1px;
  text-transform: uppercase;

  display: grid;
  grid-template-columns: 1fr max-content 1fr;
  grid-template-rows: 27px 0;
  grid-gap: 20px;
  align-items: center;
}

.sectionTitle h1:after,
.sectionTitle h1:before {
  content: " ";
  display: block;
  border-bottom: 1px solid #c50000;
  border-top: 1px solid #c50000;
  height: 5px;
  background-color: #f8f8f8;
}

.card {
  margin: 2%;
  display: inline-block;
  transition: 0.3s;
  width: 330px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
}
.plzLogin {
  border-radius: 30px;
  background-color: rgba(255, 255, 255, 0.192);
  box-shadow: 0 8px 12px 0 rgba(0, 0, 0, 0.1);
  width: 50%;
  height: 200px;
  margin-top: 4%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
