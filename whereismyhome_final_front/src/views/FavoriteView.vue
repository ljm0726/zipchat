<template>
	<div class="home">
		<b-container class="bv-example-row">
			<div style="display: flex; flex-direction: row">
				<div style="width: 45%">
					<div class="map-dropdown">
						<fieldset id="favoriteList">
							<select
								v-model="selectedDong"
								@change="changeDong"
								class="array-select form-control form-select"
								aria-label="example"
							>
								<option value="null" selected disabled hidden>관심 지역 선택</option>
								<option v-for="(item, index) in favoriteDongList" :key="index" :value="item">
									{{ item.dong }}
								</option>
							</select>
						</fieldset>
					</div>
					<!-- <component :is="FavoriteList"></component> -->
					<favorite-list2 />
				</div>
				<div style="margin-left: 10%; width: 55%">
					<favorite-news></favorite-news>
					<br />
					<b-card :title="title">
						<favorite-chart></favorite-chart>
					</b-card>

					<!-- <favorite-chart></favorite-chart> -->
					<!-- <br />
					<favorite-weather></favorite-weather>
					<br />
					<favorite-slide></favorite-slide> -->
				</div>
			</div>
		</b-container>
	</div>
</template>
<script>
import FavoriteList2 from "@/components/favorite/FavoriteList.vue";
import FavoriteNews from "@/components/favorite/FavoriteNews.vue";
import FavoriteChart from "@/components/favorite/FavoriteChart.vue";
// import FavoriteSlide from "@/components/favorite/FavoriteSlide.vue";
// import FavoriteWeather from "@/components/favorite/FavoriteWeather.vue";

import { mapActions, mapMutations, mapState } from "vuex";

const favoriteStore = "favoriteStore";
const memberStore = "memberStore";
const newsStore = "newsStore";

export default {
	name: "FavoriteView",
	data() {
		return {
			selectedDong: null,
			title: "연간 거래",
		};
	},

	created() {
		this.perPage = 10;
		this.CLEAR_FAVORITE_DONG();
		this.getFavoriteDongList(this.userInfo.id);

		this.$store.commit("newsStore/SET_KEYWORD", this.favoriteDong);
	},

	computed: {
		...mapState(memberStore, ["userInfo", "isLogin"]),
		...mapState(favoriteStore, ["favoriteDongList", "favoriteDong", "favoriteDongDealList"]),
		...mapState(newsStore, ["keyword"]),
	},

	components: {
		FavoriteList2,
		FavoriteNews,
		// FavoriteWeather,
		// FavoriteSlide,
		FavoriteChart,
	},

	methods: {
		...mapActions(favoriteStore, ["getFavoriteDongList"]),
		...mapMutations(favoriteStore, ["CLEAR_FAVORITE_DONG", "CLEAR_FAVORITE_DONG_LIST"]),

		changeDong() {
			this.$store.commit("favoriteStore/SET_FAVORITE_DONG", this.selectedDong);
			this.$store.commit("newsStore/SET_KEYWORD", this.selectedDong);
			this.$store.commit("favoriteStore/SET_FAVORITE_DONG_DEAL_LIST", this.selectedDong.dongCode);

			// console.log("F keyword", this.selectedDong);
			this.title = this.selectedDong.dong + " 연간 거래";
		},

		methodToRunOnSelect(payload) {
			this.object = payload;
			console.log(this.object);
		},
	},

	watch: {
		isLogin: function () {
			this.CLEAR_FAVORITE_DONG_LIST();
		},
	},
};
</script>

<style scoped>
.home {
	height: 100%;
	margin-left: 5%;
	margin-right: 5%;
	margin-top: 2%;
}
</style>
