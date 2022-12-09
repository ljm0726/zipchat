<template>
  <ul class="menu">
    <li class="page-item" v-if="prev">
      <router-link
        :to="`/page?no=${(startPageIndex - 1) * listRowCount}`"
        class="page-link"
        aria-label="Previous"
        @click.native="movePage(startPageIndex - 1)"
      >
        <span aria-hidden="true">&laquo;</span>
      </router-link>
    </li>

    <li
      v-for="index in pageNavi.endPageIndex - pageNavi.startPageIndex + 1"
      :key="index"
      class="page-item"
      :class="{ active: startPageIndex + index - 1 == currentPageIndex }"
    >
      <router-link
        :to="`/page?no=${(startPageIndex + index - 1) * listRowCount}`"
        class="page-link"
        @click.native="movePage(startPageIndex + index - 1)"
      >
        {{ startPageIndex + index - 1 }}
      </router-link>
    </li>

    <li class="page-item" v-if="next">
      <router-link
        :to="`/page?no=${(endPageIndex + 1) * listRowCount}`"
        class="page-link"
        aria-label="Next"
        @click.native="movePage(endPageIndex + 1)"
      >
        <span aria-hidden="true">&raquo;</span>
      </router-link>
    </li>
  </ul>
</template>

<script>
// import http from "../http-common";
import { mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "PageLink",

  data() {
    return {
      // totalListItemCount: 0,
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      // pageCount: 0,
      startPageIndex: 0,
      endPageIndex: 0,
      prev: false,
      next: false,
    };
  },

  computed: {
    ...mapState(boardStore, ["pageNavi"]),
    // ...mapState(boardStore, ["pageNav"]),
  },

  methods: {
    movePage(param) {
      this.currentPageIndex = param;
      this.initComponent();
    },

    //initComponent 확인해서 movePage param 확인하기
    initUI() {
      this.pageLinkCount = this.pageNavi.naviSize;
      this.pageCount = this.pageNavi.totalPageCount;
      this.listRowCount = 10; //원본 확인 해보기
      this.currentPageIndex = this.pageNavi.currentPage;

      if (this.currentPageIndex % this.pageLinkCount == 0) {
        this.startPageIndex =
          (this.currentPageIndex / this.pageLinkCount - 1) *
            this.pageLinkCount +
          1;
      } else {
        this.startPageIndex =
          Math.floor(this.currentPageIndex / this.pageLinkCount) *
            this.pageLinkCount +
          1;
      }

      if (this.currentPageIndex % this.pageLinkCount == 0) {
        //10, 20...맨마지막
        this.endPageIndex =
          (this.currentPageIndex / this.pageLinkCount - 1) *
            this.pageLinkCount +
          this.pageLinkCount;
      } else {
        this.endPageIndex =
          Math.floor(this.currentPageIndex / this.pageLinkCount) *
            this.pageLinkCount +
          this.pageLinkCount;
      }

      if (this.endPageIndex > this.pageCount) {
        this.endPageIndex = this.pageCount;
      }

      if (this.currentPageIndex <= this.pageLinkCount) {
        this.prev = false;
      } else {
        this.prev = true;
      }

      if (this.endPageIndex >= this.pageCount) {
        this.endPageIndex = this.pageCount;
        this.next = false;
      } else {
        this.next = true;
      }
    },
  },
  created() {
    this.listRowCount = this.pid;
    this.initUI();
  },
  mounted() {
    this.$router.push("page?no=" + this.listRowCount);
  },
};
</script>
<style scoped>
ul.menu {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: rgb(161, 158, 158);
}

.menu li {
  float: left;
}

.menu li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.menu li a:hover:not(.active) {
  background-color: #111;
}

.menu .active {
  background-color: #4caf50;
}
</style>
