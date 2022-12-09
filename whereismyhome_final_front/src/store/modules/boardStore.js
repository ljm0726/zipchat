import { listArticle, getArticle } from "@/api/board.js";

const boardStore = {
	namespaced: true,
	state: {
		boardList: [],
		pageNavi: {},
		boardArticle: {},
	},
	getters: {},
	mutations: {
		SET_PAGE_NAV: (state, pageNav) => {
			state.pageNavi = pageNav;
		},
		SET_BOARD_LIST: (state, boardList) => {
			state.boardList = boardList;
		},
		SET_BOARD_ARTICLE: (state, boardArticle) => {
			state.boardArticle = boardArticle;
		},
	},

	actions: {
		getListArticle({ commit }, pageNav) {
			// console.log("params", pageNav);
			listArticle(
				pageNav,
				({ data }) => {
					commit("SET_BOARD_LIST", data.boardList);
					commit("SET_PAGE_NAV", data.pageNavigation);
				},
				(error) => {
					console.log(error);
				}
			);
		},
		getArticle({ commit }, articleNo) {
			getArticle(
				articleNo,
				({ data }) => {
					// console.log(data);
					commit("SET_BOARD_ARTICLE", data);
				},
				(error) => {
					console.log(error);
				}
			);
		},
	},
};

export default boardStore;
