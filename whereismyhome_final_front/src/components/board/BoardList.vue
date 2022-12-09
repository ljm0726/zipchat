<template>
  <b-container>
    <b-row class="mb-5">
      <b-col>
        <div class="boardTitle">
          <h3 class="title">공지사항</h3>
        </div>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <div>
          <div class="input-group input-group-sm">
            <b-form-select v-model="key" :options="options"></b-form-select>
            <input type="text" v-model="word" placeholder="검색키워드" />
            <button
              id="btn-search"
              class="btn btn-dark"
              type="button"
              @click="search"
            >
              검색
            </button>
          </div>
        </div>
      </b-col>
      <b-col class="writeBtnWrapper text-right">
        <button
          class="writeBtn"
          v-if="userInfo != null && userInfo.roll == 3"
          @click="moveWrite()"
        >
          글쓰기
        </button>
      </b-col>
    </b-row>
    <hr />
    <b-row class="mt-5">
      <b-col>
        <div>
          <b-table
            responsive="sm"
            id="my-table"
            :items="boardList"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="fields"
            hover
          >
            <template #cell(subject)="data">
              <router-link
                class="articleTitle"
                :to="{
                  name: 'boardview',
                  params: { articleNo: data.item.articleNo },
                }"
                >{{ data.item.subject }}</router-link
              >
            </template>
            <template #cell(registerTime)="data">
              {{ data.item.registerTime | dateFormat }}
            </template>
          </b-table>
          <b-pagination
            class="mt-5"
            page-class="customPage"
            align="center"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="boardList"
          >
          </b-pagination>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
import { mapActions, mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  components: {},
  data() {
    return {
      fields: [
        { key: "articleNo", label: "번호" },
        { key: "userId", label: "ID" },
        { key: "subject", label: "제목" },
        { key: "hit", label: "조회수" },
        { key: "registerTime", label: "등록일" },
      ],
      options: ["제목", "작성자"],
      key: "제목",
      word: "",

      pageNav: {
        pg: 1,
        spp: 100,
        start: 0,
        key: "",
        word: "",
      },

      currentPage: 1,
      perPage: 10,
      totalRows: 100,
    };
  },
  created() {
    this.getListArticle(this.pageNav);
  },
  computed: {
    ...mapState(boardStore, ["boardList"]),
    ...mapState(memberStore, ["isLogin", "userInfo"]),

    rows() {
      return this.boardList.length;
    },
  },
  methods: {
    ...mapActions(boardStore, ["getListArticle"]),

    goDetail(value, index) {
      this.$router.push({ name: "boardview", params: value });
    },

    changePage() {
      this.getListArticle();
    },

    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },

    search() {
      if (this.key === "제목") {
        this.pageNav.key = "subject";
      } else {
        this.pageNav.key = "id";
      }
      this.pageNav.word = this.word;
      // console.log(this.pageNav);

      this.getListArticle(this.pageNav);
    },
  },

  filters: {
    dateFormat(regtime) {
      const today = moment().format("YYYY-MM-DD");
      const regtime2 = moment(new Date(regtime)).format("YYYY-MM-DD");

      if (moment(today).isAfter(regtime2)) {
        //today가 작성일보다 이후면
        return moment(new Date(regtime)).format("YY.MM.DD");
      }
      return moment(new Date(regtime)).format("HH:mm");
    },
  },
};
</script>

<style>
.boardTitle {
  display: flex;
  justify-content: center;
}
.title {
  padding-bottom: 5px;
  border-bottom: 3px solid rgb(6, 51, 134);
  width: 200px;
}
.articleTitle {
  text-decoration-line: none;
  color: black;
  font-weight: bold;
}
.writeBtnWrapper {
  display: flex;
  justify-content: flex-end;
  right: 0;
}
.writeBtn {
  width: 80px;
  border: 1.5px solid rgb(6, 51, 134);
  border-radius: 5px;
  background-color: white;
}
.writeBtn:hover {
  border: 1.5px solid rgb(6, 51, 134);
  background-color: rgb(6, 51, 134);
  color: white;
}
.page-item .page-link {
  /* background-color: rgb(253, 253, 205);
  border-color: rgb(235, 235, 235); */
  color: black;
}
.customPage.page-item.active .page-link {
  background-color: rgba(6, 51, 134, 0.753);
  border-color: rgba(255, 255, 255, 0.753);
  color: black;
}
</style>
