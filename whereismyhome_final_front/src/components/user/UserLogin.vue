<template>
  <div class="home">
    <div class="login-box">
      <h2>Hello</h2>
      <br/>
      <form>
        <div class="login-components-wrapper">
          <input type="text" id="id" v-model="user.id" name="id" required @keyup.enter="confirm">
          <label>ID</label>
        </div>
        <div class="login-components-wrapper">
          <input type="password" id="pass"
                  v-model="user.pass" name="pass" required @keyup.enter="confirm">
          <label>Password</label>
        </div>
         <div class="signup" @click="movePage">SignUp</div>
        <a @click="confirm">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Login
        </a>
      </form>
    </div>
   
    
    <!-- <div class="container">
      <div class="input-form-backgroud row">
        <div class="input-form col-md-12 mx-auto">
          <h4 class="m3-5">LOGIN</h4>
          <div id="login-form-wrapper">
            <form
              id="login-form"
              method="POST"
              action=""
              name="login-form"
              class="login-form"
            >
              <b-alert
                show
                variant="danger"
                v-if="isLoginError"
                style="width: 70%; font-size: 15px"
                >아이디 또는 비밀번호를 확인하세요.</b-alert
              >
              <div class="bind mb-3">
                <label for="id">아이디</label>
                <input
                  id="id"
                  v-model="user.id"
                  type="text"
                  placeholder="id"
                  class="form-control"
                  required
                  @keyup.enter="confirm"
                />
              </div>

              <div class="bind mb-3">
                <label for="pass">비밀번호</label>
                <input
                  id="pass"
                  v-model="user.pass"
                  type="password"
                  placeholder="password"
                  class="form-control"
                  required
                  @keyup.enter="confirm"
                />
              </div>
              <div class="btnWrapper">
                <button
                  type="button"
                  class="btn btn-outline-dark"
                  @click="confirm"
                >
                  로그인
                </button>
                <button
                  type="button"
                  class="btn btn-outline-dark"
                  @click="movePage"
                >
                  회원 가입
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        id: null,
        pass: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);

      if(this.isLoginError) alert("정보가 일치하지 않습니다.");

      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scoped>
.home {
  height: 100vh;
  margin-top: 0%;
 font-family: sans-serif;
  background: linear-gradient(#141e30, #a3afbe);  
  /* background-image: url("../../assets/signupImg.jpg");
  background-size: cover;
  background-repeat: no-repeat; */
}


.login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0,0,0,.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0,0,0,.6);
  border-radius: 10px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: rgb(134, 137, 167);
  text-align: center;
  font-family: sans-serif;
}

.login-box .login-components-wrapper {
  position: relative;
}

.login-box .login-components-wrapper input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.login-box .login-components-wrapper label {
  position: absolute;
  top:0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: .5s;
}

.login-box .login-components-wrapper input:focus ~ label,
.login-box .login-components-wrapper input:valid ~ label {
  top: -20px;
  left: 0;
  color: #a3a9f3;
  font-size: 12px;
}

.login-box form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #a3a9f3;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: .5s;
  margin-top: 40px;
  letter-spacing: 4px
}

.login-box a:hover {
  background: #a3a9f3;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #a3a9f3,
              0 0 25px #a3a9f3,
              0 0 50px #a3a9f3,
              0 0 100px #a3a9f3;
  cursor: pointer;
}

.login-box a span {
  position: absolute;
  display: block;
}

.login-box a span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #a3a9f3);
  animation: btn-anim1 1s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,100% {
    left: 100%;
  }
}

.login-box a span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #a3a9f3);
  animation: btn-anim2 1s linear infinite;
  animation-delay: .25s
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,100% {
    top: 100%;
  }
}

.login-box a span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #a3a9f3);
  animation: btn-anim3 1s linear infinite;
  animation-delay: .5s
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,100% {
    right: 100%;
  }
}

.login-box a span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #a3a9f3);
  animation: btn-anim4 1s linear infinite;
  animation-delay: .75s
}

.signup{
  display: flex;
  justify-content: flex-end;
  color: white;
  cursor: pointer;
}

.signup:hover{
  text-decoration: underline;
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,100% {
    bottom: 100%;
  }
}
</style>
