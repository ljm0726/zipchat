<template>
  <div class="col-lg-3 col-sm-6 mb-4 mx-3">
    <div class="portfolio-item">
      <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1">
        <div class="card">
          <main-slide :mainDong="this.mainDong"></main-slide>

        </div>
      </a>
    </div>
  </div>
</template>

<script>
import MainSlide from "@/components/mainComponents/item/MainSlide.vue";
import { mapActions, mapState } from "vuex";
const favoriteStore = "favoriteStore";
const houseStore = "houseStore";

export default {
  name: "MainFavoriteListItem",
  data() {
    return {
      favoriteList: {},
      
      dongName: null,
    };
  },
  components: {
    MainSlide,
  },
  props: {
    favoriteMainItem: {},
    mainDong: null,
  },


  created() {},
  computed: {},
  methods: {
    // pageClick: function (button, page) {
    // 	this.currentPage = page;
    // 	this.getNoticeListByPage(page);
    // },
  },

  filters: {},
};
</script>

<style>
.boardListItemWrapper {
  border: 1px solid rgb(202, 202, 202);
  width: 50%;
}
.noticeTitle {
  text-decoration: none;
  color: rgb(107, 107, 107);
}
</style>
