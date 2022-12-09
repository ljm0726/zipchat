<template>
	<div class="home">
		<div class="container">
			<div class="input-form-backgroud">
				<div class="row input-form col-md-12 mx-auto">
					<div class="col left-side">
						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">아이디</div>
							<input
								id="id"
								v-model="userInfo.id"
								type="text"
								placeholder="id"
								class="mypage-compo-con"
								readonly="readonly"
								value="${userInfo.id}"
							/>
						</div>

						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">비밀번호</div>
							<input
								id="pass"
								v-model="pass"
								type="password"
								placeholder="password"
								class="mypage-compo-con"
								@keyup="passwordcheck"
							/>
						</div>

						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">비밀번호 확인</div>
							<input
								id="repass"
								v-model="repass"
								type="password"
								placeholder="repassword"
								class="mypage-compo-con"
								@keyup="passwordcheck"
							/>
						</div>
						<div id="check-msg">
							<span>{{ passCheckMsg }}</span>
						</div>
						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">이름</div>
							<input
								id="name"
								v-model="name"
								type="text"
								placeholder="name"
								class="mypage-compo-con"
							/>
						</div>

						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">이메일</div>
							<input
								id="email"
								v-model="email"
								type="text"
								placeholder="email"
								class="mypage-compo-con"
							/>
						</div>

						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">주소</div>
							<input
								id="address"
								v-model="address"
								type="text"
								placeholder="address"
								class="mypage-compo-con"
							/>
						</div>

						<div class="mypage-compo-wrapper">
							<div class="mypage-compo-title">연락처</div>
							<input
								id="phone"
								v-model="phone"
								type="tel"
								placeholder="phone number"
								class="mypage-compo-con"
							/>
						</div>
					</div>
					<div class="col right-side">
						<h4 class="myPageTitle">정보 수정</h4>
						<div class="btn-wrapper">
							<router-link
								class="articleTitle"
								:to="{
									name: 'update',
								}"
							>
								<button class="custom-btn" @click="update">수정 완료</button>
							</router-link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import http from "@/api/http.js";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
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
			passCheckMsg: "",
		};
	},
	created() {
		this.id = this.userInfo.id;
		this.name = this.userInfo.name;
		this.email = this.userInfo.email;
		this.address = this.userInfo.address;
		this.phone = this.userInfo.phone;
	},
	computed: {
		...mapState(memberStore, ["userInfo"]),
	},
	methods: {
		...mapActions(memberStore, ["userLogout"]),
		passwordcheck() {
			if (this.pass != "" && this.pass === this.repass) {
				this.passCheckMsg = "비밀번호가 일치합니다.";
				this.userPwdChk = true;
			} else if (this.pass != this.repass && this.repass != null) {
				this.passCheckMsg = "비밀번호가 일치하지 않습니다.";
				this.userPwdChk = false;
			} else {
				this.passCheckMsg = "";
			}
		},
		update(event) {
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
					.put("/user/update", {
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
							this.userLogout(this.userInfo.id);
							sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
							sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
							alert("회원정보 수정 완료! 다시 로그인 해주세요.");
							this.$router.push("/user/login/");
						}
					})
					.catch((error) => {
						console.log("UserRegisterVue: error : ");
						console.log(error);
						alert("회원정보 수정 실패");
					});
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

.right-side {
	background-color: #0a0024;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
.left-side {
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
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
	font-size: 5px;
}
.mypage-compo-con {
	width: 70%;
	display: flex;
	justify-content: center;
	align-items: center;
}

#check-msg {
	display: flex;
	justify-content: flex-end;
	align-items: flex-end;
	font-size: 4px;
}
</style>
