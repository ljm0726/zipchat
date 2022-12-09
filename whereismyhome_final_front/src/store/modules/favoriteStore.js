import { favoriteList, favoriteDongList, mainFavoriteList } from "@/api/favorite";

const favoriteStore = {
	namespaced: true,
	state: {
		favoriteList: [],
		favoriteDongList: [],
		favoriteDongDealList: [],
		favoriteDong: {},
		mainFavoriteList: [],
		pageNavi: {},
	},
	getters: {},
	mutations: {
		SET_PAGE_NAV: (state, pageNav) => {
			state.pageNavi = pageNav;
		},
		SET_FAVORITE_LIST: (state, favoriteList) => {
			state.favoriteList = favoriteList;
		},
		SET_FAVORITE_DONG_LIST: (state, favoriteDongList) => {
			state.favoriteDongList = favoriteDongList;
		},
		SET_FAVORITE_DONG_DEAL_LIST: (state, favoriteDongDealList) => {
			state.favoriteDongDealList = favoriteDongDealList;
		},
		SET_MAIN_FAVORITE_LIST: (state, mainFavoriteList) => {
			state.mainFavoriteList = mainFavoriteList;
		},
		SET_FAVORITE_DONG: (state, favoriteDong) => {
			state.favoriteDong = favoriteDong;
		},
		CLEAR_FAVORITE_DONG: (state) => {
			state.favoriteDong = {};
		},
		CLEAR_FAVORITE_LIST(state) {
			state.favoriteList = [];
		},
		CLEAR_FAVORITE_DONG_LIST(state) {
			state.favoriteDongList = [];
		},
		CLEAR_FAVORITE_DONG_DEAL_LIST(state) {
			state.favoriteDongDealList = [];
		},
	},

	actions: {
		getFavoriteList({ commit }, dongCode) {
			favoriteList(
				dongCode,
				({ data }) => {
					console.log(data["dealInfoList"]);
					commit("SET_FAVORITE_LIST", data["dealInfoList"]);
					commit("SET_FAVORITE_DONG_DEAL_LIST", data["dealCount"]);
				},
				(error) => {
					console.log(error);
				}
			);
		},
		getFavoriteDongList({ commit }, id) {
			// console.log(id, "id");
			favoriteDongList(
				id,
				({ data }) => {
					commit("SET_FAVORITE_DONG_LIST", data);
				},
				(error) => {
					console.log(error);
				}
			);
		},
		getMainFavoriteList({ commit }, id) {
			mainFavoriteList(
				id,
				({ data }) => {
					commit("SET_MAIN_FAVORITE_LIST", data);
				},
				(error) => {
					console.log(error);
				}
			);
		},
	},
};

export default favoriteStore;
