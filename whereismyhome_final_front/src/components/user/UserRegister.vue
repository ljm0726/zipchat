<template>
  <div class="home">
    <div class="login-box">
      <h2>Welcome</h2>
      <br/>
      <form>
        <div class="login-components-wrapper">
          <input type="text" id="id" v-model="id" name="id" required @keyup="idCheck">
          <label>ID</label>
        </div>
          <div id="check-msg" v-if="userIdChk">아이디 사용 가능</div>
          <div id="check-msg" v-else>아이디 사용 불가</div>
        <div class="login-components-wrapper">
          <input type="password" id="pass"
                  v-model="pass" name="pass" required @keyup="passwordcheck">
          <label>Password</label>
        </div>
        <div class="login-components-wrapper">
          <input type="password" id="repass"
                  v-model="repass" name="repass" required @keyup="passwordcheck">
          <label>Confirm Password</label>
        </div>
        <div id="check-msg">{{ passCheckMsg }}</div>
        <div class="login-components-wrapper">
          <input type="text" id="name" v-model="name" name="name" required>
          <label>이름</label>
        </div>
        <div class="login-components-wrapper">
          <input type="text" id="email" v-model="email" name="email" required>
          <label>이메일</label>
        </div>
        <div class="login-components-wrapper">
          <input type="text" id="address" v-model="address" name="address" required>
          <label>주소</label>
        </div>
        <div class="login-components-wrapper">
          <input type="text" id="phone" v-model="phone" name="phone" required>
          <label>연락처</label>
        </div>
        <a @click="regist">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          회원가입
        </a>
      </form>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";
export default {
  name: "UserRegister",
  components: {},
  data() {
    return {
      id: "",
      pass: "",
      repass: "",
      name: "",
      email: "",
      address: "",
      phone: "",
      userIdChk: false,
      userPwdChk: true,
      idCheckMsg: "-",
      passCheckMsg: "비밀번호가 일치하지 않습니다.",
    };
  },
  methods: {
    idCheck() {
      if (this.id == "" || this.id == null) {
        this.userIdChk = false;
        return;
      }
      http
        .get(`/user/${this.id}`)
        .then(({ data }) => {
          // console.log(data);
          if (data === "success") {
            this.userIdChk = true;
          } else {
            this.userIdChk = false;
          }
        })
        .catch((error) => console.log("id체크 실패.."));
    },
    passwordcheck() {
      if (this.pass === this.repass) {
        this.passCheckMsg = "비밀번호가 일치합니다.";
        this.userPwdChk = true;
      } else if (this.pass != this.repass) {
        this.passCheckMsg = "비밀번호가 일치하지 않습니다.";
        this.userPwdChk = false;
      }
    },
    regist(event) {
      event.preventDefault();
      // 빈 칸이 있는지 확인후 있으면 알림창
      if (
        this.id == "" ||
        this.pass == "" ||
        this.name == "" ||
        this.address == "" ||
        this.phone == ""
      ) {
        alert("빈 칸이 없도록 입력해주세요.");
        return;
      } else if (!this.userPwdChk) {
        alert("비밀번호를 다시 확인해주세요.");
        return;
      } else {
        http
          .post("/user/join", {
            id: this.id,
            pass: this.pass,
            name: this.name,
            email: this.email,
            address: this.address,
            phone: this.phone,
          })
          .then(({ data }) => {
            // console.log(data);
            if (data === "success") {
              alert("회원가입이 완료되었습니다.");
              this.$router.push("/user/login/");
            }
          })
          .catch((error) => {
            console.log("UserRegisterVue: error : ");
            console.log(error);
            alert("회원가입 실패");
          });
      }
    },
  },
};
</script>

<style scoped>
.home {
  height: 150vh;
   font-family: sans-serif;
  background: linear-gradient(#141e30, #a3afbe);  

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
   margin-top: 220px;
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
  color: #03e9f4;
  font-size: 12px;
}
/* 03e9f4 */
.login-box form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #03e9f4;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: .5s;
  margin-top: 40px;
  letter-spacing: 4px
}

.login-box a:hover {
  background: #03e9f4;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03e9f4,
              0 0 25px #03e9f4,
              0 0 50px #03e9f4,
              0 0 100px #03e9f4;
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
  background: linear-gradient(90deg, transparent, #03e9f4);
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
  background: linear-gradient(180deg, transparent, #03e9f4);
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
  background: linear-gradient(270deg, transparent, #03e9f4);
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
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: btn-anim4 1s linear infinite;
  animation-delay: .75s
}

#check-msg{
  color: rgb(245, 218, 218);
  display: flex;
  justify-content: flex-end;
  
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
