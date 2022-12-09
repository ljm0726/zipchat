import { newsList } from "@/api/news";

const newsStore = {
	namespaced: true,
	state: {
		newsList: [],
		pageNavi: {},
		keyword: null,
	},
	getters: {},
	mutations: {
		SET_PAGE_NAV: (state, pageNav) => {
			state.pageNavi = pageNav;
		},
		SET_NEWS_LIST: (state, newsList) => {
			state.newsList = newsList;
		},
		SET_KEYWORD: (state, keyword) => {
			state.keyword = keyword;
		},

		CLEAR_NEWS_LIST(state) {
			state.newsList = [];
		},
	},

	actions: {
		getNewsList({ commit }, search) {
			newsList(
				search,
				({ data }) => {
					commit("SET_NEWS_LIST", data.items);
				},
				(error) => {
					console.log(error);
				}
			);
		},
	},
};

export default newsStore;
