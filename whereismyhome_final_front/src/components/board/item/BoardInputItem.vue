<template>
  <div>
    <b-container class="bv-example-row mt-3 mx-auto">
      <b-row>
        <b-card class="articleBox">
          <div class="write-titleBar">
            <label for="write-subject">제목: </label>
            <div class="write-subWrapper">
              <input
                id="write-subject"
                v-model="article.subject"
                type="text"
                required
              />
            </div>
          </div>
          <div class="write-contentsBar">
            <textarea
              id="content"
              v-model="article.content"
              placeholder="내용 입력"
              cols="100"
              rows="12"
              max-rows="15"
            ></textarea>
          </div>
        </b-card>
      </b-row>
    </b-container>
    <div class="articleBtnWrapper">
      <div>
        <b-button
          v-if="this.type === 'register'"
          variant="outline-warning"
          size="sm"
          class="articleBtn"
          @click="onSubmit"
          >글작성</b-button
        >
        <b-button
          v-else
          variant="outline-danger"
          size="sm"
          class="articleBtn"
          @click="onSubmit"
          >글수정</b-button
        >
      </div>
      <div>
        <b-button
          variant="outline-secondary"
          size="sm"
          @click="onReset"
          class="articleBtn"
          >목록</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { writeArticle, modifyArticle } from "@/api/board";
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";
const boardStore = "boardStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        userName: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(boardStore, ["boardArticle"]),
  },
  created() {
    this.article.userId = this.userInfo.id; //ID는 고정
    this.article.userName = this.userInfo.name; //ID는 고정

    if (this.type === "modify") {
      let param = this.$route.params.articleNo;
      this.getArticle(param);

      this.article = this.boardArticle;
      this.isUserid = true;
    }

  },

  methods: {
    ...mapActions(boardStore, ["getArticle", "writeArticle", "modifyArticle"]),

    onSubmit(event) {
      event.preventDefault();
      console;
      let err = true;
      let msg = "";
      !this.article.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleNo = 0;
      this.article.subject = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      let param = {
        userId: this.article.userId,
        userName: this.article.userName,
        subject: this.article.subject,
        content: this.article.content,
      };

      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        articleNo: this.article.articleNo,
        userId: this.article.userId,
        userName: this.article.userName,
        subject: this.article.subject,
        content: this.article.content,
      };

      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
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
.write-titleBar {
  border-bottom: 1px solid rgb(177, 176, 176);
  height: 15%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 15px;
}
.write-contentsBar {
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
.write-subWrapper {
  width: 90%;
}

#write-subject {
  width: 100%;
}
</style>
