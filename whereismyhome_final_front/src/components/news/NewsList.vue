<template>
  <div class="news-items-list">
    <b-container class="bv-example-row">
      <news-list-item
        v-for="(news, index) in newsList"
        :key="index"
        :news="news"
        class="news-item"
      >
      </news-list-item>
    </b-container>
  </div>
</template>

<script>
import NewsListItem from "@/components/news/item/NewsListItem.vue";
import { mapActions, mapState, mapMutations } from "vuex";

const newsStore = "newsStore";

export default {
  name: "NewsList",
  data() {
    return {
      search: "",
      abc: [],
    };
  },

  components: {
    NewsListItem,
  },

  created() {
    this.CLEAR_NEWS_LIST();
    this.search = this.keyword.dong;

    this.getNewsList(this.search);
  },
  computed: {
    ...mapState(newsStore, ["newsList", "keyword"]),
  },
  methods: {
    ...mapActions(newsStore, ["getNewsList"]),
    ...mapMutations(newsStore, ["CLEAR_NEWS_LIST"]),

  },

  watch: {
    keyword: function () {
      this.getNewsList(this.keyword.dong);
    },
  },
};
</script>

<style>
.news-item {
  height: 80px;
  border-top: 1px solid gray;
  border-bottom: 1px solid gray;
  background-color: rgba(237, 245, 250, 0.438);
}
</style>
