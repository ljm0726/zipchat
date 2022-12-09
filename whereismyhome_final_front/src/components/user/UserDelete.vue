<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>유저정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row> </b-row>
  </b-container>
</template>

<script>
import { deleteUser } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserDelete",
  created() {
    let param = this.userInfo.id;
    deleteUser(
      param,
      ({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        console.log(data);
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
          alert(msg);
          this.$router.push({ name: "main" });
        } else {
          alert(msg);
          this.$router.push({ name: "mypage" });
        }
        // 현재 route를 /list로 변경.
      },
      (error) => {
        console.log(error);
      }
    );

    this.userLogout(this.userInfo.id);
    sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
    sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
    if (this.$route.path != "/") this.$router.push({ name: "main" });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
  },
};
</script>

<style></style>
