<template>
  <div>
    <b-card>
      <div class="favorite-news-title">
        <div class="div-wrapper">
          <h1>관심 지역 소식</h1>
          <h6 v-if="favoriteDong.dong">
            {{ favoriteDong.dong }}의 최신 뉴스입니다.
          </h6>
        </div>
      </div>
      <news-list></news-list>
    </b-card>
  </div>
</template>

<script>
import NewsList from "@/components/news/NewsList.vue";
import { mapState } from "vuex";
const favoriteStore = "favoriteStore";
export default {
  name: "FavoriteChart",
  data() {
    return {};
  },
  computed: {
    ...mapState(favoriteStore, ["favoriteDong"]),
  },
  components: {
    NewsList,
  },

  methods: {},
};
</script>

<style>
.favorite-news-title {
  display: inline-block;
  margin-bottom: 30px;
}
.favorite-news-title .div-wrapper {
  color: #333;
  border-bottom: 4px solid #4584f8;
  position: relative;
}

.favorite-news-title .div-wrapper:before {
  content: " ";
  position: absolute;
  width: 0;
  height: 0;
  left: 30px;
  bottom: -30px;
  border: 15px solid;
  border-color: #4584f8 transparent transparent #4584f8;
}

.favorite-news-title .div-wrapper:after {
  content: " ";
  position: absolute;
  width: 0;
  height: 0;
  left: 34px;
  bottom: -20px;
  border: 15px solid;
  border-color: #fff transparent transparent #fff;
}
</style>
