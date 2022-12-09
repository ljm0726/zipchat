<template>
	<nav class="navbar navbar-expand-lg bg-dark fixed-top" id="header">
		<div class="container">
			<div class="navbar-brand">
				<router-link to="/" style="text-decoration: none; color: inherit"
					><img src="../assets/ZIPCHAT3.png" alt="MyHouse" />
				</router-link>
			</div>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<li class="nav-item">
						<div class="nav-link">
							<router-link to="/board" style="text-decoration: none; color: inherit"
								>Notice</router-link
							>
						</div>
					</li>
					<li class="nav-item">
						<div class="nav-link">
							<router-link to="/apt" style="text-decoration: none; color: inherit"
								>Apartment Info</router-link
							>
						</div>
					</li>
					<li class="nav-item">
						<div class="nav-link">
							<router-link to="/favorite" style="text-decoration: none; color: inherit"
								>Favorite Place</router-link
							>
						</div>
					</li>
					<li class="nav-item">
						<!-- after login -->
						<div class="userInfoWrapper" v-if="userInfo">
							<div class="nav-link">{{ userInfo.name }}({{ userInfo.id }})님 환영합니다.</div>
							<div class="userInfoItem">
								<div>
									<router-link :to="{ name: 'mypage' }" class="nav-link">MyPage</router-link>
								</div>
								<div class="nav-link" @click.prevent="onClickLogout">Logout</div>
							</div>
						</div>
						<!-- before login -->
						<b-navbar-nav class="ml-auto" v-else>
							<b-nav-item-dropdown right>
								<template #button-content>
									<b-icon icon="people" font-scale="2"></b-icon>
								</template>
								<b-dropdown-item>
									<router-link :to="{ name: 'join' }" class="link">
										<div class="dropdown-item">
											<b-icon icon="person-circle"></b-icon> 회원가입
										</div>
									</router-link>
								</b-dropdown-item>
								<b-dropdown-item>
									<router-link :to="{ name: 'login' }" class="link">
										<div class="dropdown-item">
											<b-icon icon="key"></b-icon> 로그인
										</div>
									</router-link>
								</b-dropdown-item>
							</b-nav-item-dropdown>
						</b-navbar-nav>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</template>
<script>
import { mapState, mapGetters, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";
const favoriteStore = "favoriteStore";
export default {
	name: "TheHeader",
	data() {
		return {};
	},
	computed: {
		...mapState(memberStore, ["isLogin", "userInfo"]),
		...mapGetters(["checkUserInfo"]),
	},
	watch: {},
	methods: {
		...mapActions(memberStore, ["userLogout"]),
		...mapMutations(favoriteStore, ["CLEAR_FAVORITE_LIST", "CLEAR_FAVORITE_DONG_LIST"]),
		onClickLogout() {
			this.userLogout(this.userInfo.id);
			sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
			sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기

			this.CLEAR_FAVORITE_LIST();
			this.CLEAR_FAVORITE_DONG_LIST();

			// console.log("reset", this.favoriteDongList);

			if (this.$route.path != "/") this.$router.go({ name: "main" });
			else {
				this.$router.go();
			}
		},
	},
};
</script>

<style>
#header {
	height: 90px;
	padding-top: 1rem;
	padding-bottom: 1rem;
	/* background-color: #212529; */
}
#header .navbar-brand {
	color: #ffc800;
	font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue",
		Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
	font-weight: 700;
	letter-spacing: 0.0625em;
	text-transform: uppercase;
}
#header .navbar-brand img {
	height: 50px;
}
.nav-item {
	display: flex;
	align-items: center;
	margin-right: 3rem;
}
.userInfoWrapper {
	display: flex;
	flex-direction: column;
}
.userInfoItem {
	display: flex;
	justify-content: space-around;
}
.nav-item .nav-link {
	font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue",
		Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
	font-size: 0.95rem;
	color: antiquewhite;
	letter-spacing: 0.0625em;
}
.nav-item .nav-link:hover {
	color: antiquewhite;
	font-weight: bold;
}
#logo {
	width: 120px;
}

.link {
	text-decoration: none;
}
.dropdown-item{
	background-color: black;
}

</style>
