<template>
  <div>
    <b-container class="bv-example-row mt-3 mx-auto">
      <b-row>
        <b-card class="articleBox">
          <div class="titleBar">{{ boardArticle.subject }}</div>
          <div class="sideBar">작성자: {{ boardArticle.userId }}</div>
          <div class="sideBar">조회수: {{ boardArticle.hit }}</div>
          <div class="sideBar">작성일: {{ boardArticle.registerTime }}</div>
          <div class="contentsBar" v-html="message"></div>
        </b-card>
      </b-row>
    </b-container>
    <div class="articleBtnWrapper">
      <div>
        <b-button
          variant="outline-secondary"
          size="sm"
          @click="moveList"
          class="articleBtn"
          >목록</b-button
        >
      </div>
      <div>
        <b-button
          v-if="userInfo.id === boardArticle.userId"
          variant="outline-warning"
          size="sm"
          @click="moveModifyArticle()"
          class="articleBtn"
          >글수정</b-button
        >
        <b-button
          variant="outline-danger"
          size="sm"
          @click="deleteArticle()"
          v-if="userInfo.id === boardArticle.userId"
          class="articleBtn"
          >글삭제</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      param: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(boardStore, ["boardArticle"]),
    message() {
      if (this.boardArticle.content)
        return this.boardArticle.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {},
  mounted() {
    this.param = this.$route.params;
    this.getArticle(this.param.articleNo);
  },
  methods: {
    ...mapActions(boardStore, ["getArticle", "modifyArticle"]),

    moveModifyArticle() {
      console.log("!!!!!!!! " + this.param);
      this.$router.replace({
        name: "boardmodify",
        params: { articleNo: this.param },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleNo: this.param.articleNo },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style>
.bv-example-row {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* padding-top: 2%; */
}
.articleBox {
  width: 500px;
  height: 500px;
  padding: 40px;

  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
  -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
  box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
}
.titleBar {
  border-bottom: 1px solid rgb(177, 176, 176);
  height: 15%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 20px;
}
.sideBar {
  display: flex;
  justify-content: flex-start;
  border-bottom: 1px solid rgb(177, 176, 176);
}
.contentsBar {
  /* border: 1px solid rgb(177, 176, 176); */
  display: flex;
  justify-content: flex-start;
  margin-top: 20px;
}
.articleBtnWrapper {
  display: flex;
  margin-top: 20px;
  justify-content: space-between;
}
.articleBtnWrapper {
  margin-right: 10px;
}
.articleBtn {
  width: 80px;
  margin-left: 10px;
  margin-right: 10px;
}
</style>
