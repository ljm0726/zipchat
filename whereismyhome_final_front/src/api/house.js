import { apiInstance, houseInstance, weatherInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();
const weather = weatherInstance();

function sidoList(success, fail) {
  api.get(`/apt/sido`).then(success).catch(fail);
}

function gugunList(code, success, fail) {
  api.get(`/apt/gugun/${code}`).then(success).catch(fail);
}

function dongList(code, success, fail) {
  api.get(`/apt/dong/${code}`).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api
    .get(`/apt/apt-list/${params.regCode}/${params.year}/${params.month}`)
    .then(success)
    .catch(fail);
}

function houseListOneByLatLng(params, success, fail) {
  api
    .get(`/apt/near-apt-one-by-latlng/${params.lat}/${params.lng}`)
    .then(success)
    .catch(fail);
}

function houseListByLatLng(params, success, fail) {
  api
    .get(`/apt/near-apt-by-latlng/${params.lat}/${params.lng}/${params.apartmentName}`)
    .then(success)
    .catch(fail);
}

function busStationList(params, success, fail) {
  api.get(`/apt/near-bus/${params.lat}/${params.lng}`).then(success).catch(fail);
}

function schoolList(params, success, fail) {
  api.get(`/apt/near-school/${params.lat}/${params.lng}`).then(success).catch(fail);
}

function addMyDong(params, success, fail) {
  api
    .post(`/apt/add-my-dong/${params.userId}/${params.dongCode}`)
    .then(success)
    .catch(fail);
}
function delMyDong(params, success, fail) {
  api
    .delete(`/apt/del-my-dong/${params.userId}/${params.dongCode}`)
    .then(success)
    .catch(fail);
}

function isFavorite(params, success, fail) {
  api
    .get(`/apt/isFavorite/${params.userId}/${params.dongCode}`)
    .then(success)
    .catch(fail);
}

function dongWeather(params, success, fail) {
  weather.get(``, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListOneByLatLng,
  houseListByLatLng,
  busStationList,
  schoolList,
  addMyDong,
  delMyDong,
  dongWeather,
  isFavorite,
};
