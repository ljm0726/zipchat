import { apiInstance } from "./index.js";

const api = apiInstance();

function newsList(search, success, fail) {
	api.get(`/news/${search}`).then(success).catch(fail);
}

export { newsList };
