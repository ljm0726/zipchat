<template>
  <b-row
    class="m-2"
    @click="selectFavorite"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    style="width: 590px"
  >
    <a :href="news.originallink" class="textLine">
      <b-col>
        <div class="news-item-title">[<b v-html="this.title"></b>]</div>
        <div class="news-item-date">
          {{ news.pubDate | date }}
        </div>
      </b-col>
    </a>
  </b-row>
</template>

<script>
export default {
  name: "NewsListItem",
  data() {
    return {
      isColor: false,
      title: String,
    };
  },
  props: {
    news: Object,
  },

  created() {
    this.title = this.news.title;
  },

  methods: {
    selectFavorite() {},
    colorChange(flag) {
      this.isColor = flag;
    },
  },

  watch: {
    news: function () {
      this.title = this.news.title;
    },
  },

  filters: {
    date(value) {
      return value.toString().substr(0, 25);
    },
  },
};
</script>

<style scoped>
.mouse-over-bgcolor {
  background-color: rgb(214, 227, 231);
}
.textLine {
  text-decoration: none;
  display: flex;
  align-items: center;
}
.news-item-title {
  color: black;
  font-size: 15px;
}
.news-item-date {
  color: rgb(151, 151, 151);
  font-size: 13px;
  margin-top: 5px;
}
</style>
