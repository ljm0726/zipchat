import { apiInstance } from "./index.js";

const api = apiInstance();

function favoriteList(dong, success, fail) {
  api.get(`/apt/my-list/${dong.dongCode}`).then(success).catch(fail);
}

function favoriteDongList(id, success, fail) {
  api.get(`/apt/myFavorite/${id}`).then(success).catch(fail);
}

function mainFavoriteList(dongCode, success, fail) {
  api.get(`/apt/mainFavoriteList/${dongCode}`).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

export { favoriteList, favoriteDongList, mainFavoriteList, deleteArticle };
