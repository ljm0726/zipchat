<template>
  <div class="home">
    <div class="container">
      <div class="input-form-backgroud">
        <div class="row input-form col-md-12 mx-auto">
          <div class="col left-side">
            <h4 class="myPageTitle">My Page</h4>
            <div class="btn-wrapper">
              <router-link
                class="articleTitle"
                :to="{
                  name: 'update',
                }"
              >
                <button class="custom-btn">정보수정</button>
              </router-link>
              <button class="custom-btn" @click="goDel">회원탈퇴</button>
            </div>
          </div>
          <div class="col right-side">
            <div class="mypage-compo-wrapper">
              <div class="mypage-compo-title">아이디</div>
              <div class="mypage-compo-con">
                {{ userInfo.id }}
              </div>
            </div>

            <div class="mypage-compo-wrapper">
              <div class="mypage-compo-title">이름</div>
              <div class="mypage-compo-con">{{ userInfo.name }}</div>
            </div>

            <div class="mypage-compo-wrapper">
              <div class="mypage-compo-title">이메일</div>
              <div class="mypage-compo-con">{{ userInfo.email }}</div>
            </div>

            <div class="mypage-compo-wrapper">
              <div class="mypage-compo-title">주소</div>
              <div class="mypage-compo-con">{{ userInfo.address }}</div>
            </div>

            <div class="mypage-compo-wrapper">
              <div class="mypage-compo-title">연락처</div>
              <div class="mypage-compo-con">{{ userInfo.phone }}</div>
            </div>

            <div class="mypage-compo-wrapper">
              <div class="mypage-compo-title">가입일</div>
              <div class="mypage-compo-con">{{ userInfo.joinDate }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  created() {
    // console.log(this.userInfo);
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    goDel() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.push({ name: "delete" });
      }
    },
  },
};
</script>

<style scoped>
.home {
  height: 100vh;
  background: linear-gradient(#fafafa, #d8d8d8);
}

.input-form {
  width: 680px;
  margin-top: 10%;
  background: #fff;
  height: 400px;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
  -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
  box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
}

.left-side {
  background-color: #0a0024;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.right-side {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.myPageTitle {
  font-size: 50px;
  font-weight: bold;
  color: rgb(236, 236, 236);
}
.btn-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}
.custom-btn {
  border: 1px solid rgb(202, 202, 202);
  box-shadow: 0 8px 20px 0 rgba(238, 238, 238, 0.15);
  border-radius: 10px;
  margin-left: 5px;
  margin-right: 5px;
  background-color: white;
}
.mypage-compo-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  border: 1px solid #0a0024;
  border-radius: 5px;
  margin: 10px 0%;
  width: 90%;
  height: 30px;
}

.mypage-compo-title {
  border-right: 1px solid #0a0024;
  background-color: #0a0024;
  color: white;
  width: 30%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.mypage-compo-con {
  width: 70%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
