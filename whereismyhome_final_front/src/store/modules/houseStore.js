import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListOneByLatLng,
  houseListByLatLng,
  busStationList,
  schoolList,
  addMyDong,
  dongWeather,
  delMyDong,
  isFavorite,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시도 선택" }],
    guguns: [{ value: null, text: "구군 선택" }],
    dongs: [{ value: null, text: "동 선택" }],
    housesFrom: 1, //0=동코드 1=드래그
    houses: [],
    housePrices: [],
    latitude: 0,
    longitude: 0,

    modalVisiable: false,

    busStatus: false,
    schoolStatus: false,
    buses: [],
    schools: [],
    BK9Status: false,
    PM9Status: false,
    CE7Status: false,
    CS2Status: false,

    selectedHouseLat: 0,
    selectedHouseLng: 0,
    selectedHouseName: "",
    busesTop3: [],
    schoolsTop3: [],

    weather: {},

    favoriteChk: false,
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시도 선택" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구군 선택" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "동 선택" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_HOUSE_PRICE_LIST(state) {
      state.housePrices = [];
      state.housePrices = null;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_HOUSE_PRICE_LIST(state, housePrices) {
      state.housePrices = housePrices;
    },
    SET_LATITUDE(state, latitude) {
      state.latitude = latitude;
    },
    SET_LONGITUDE(state, longitude) {
      state.longitude = longitude;
    },
    SET_HOUSESFROM(state, housesFrom) {
      state.housesFrom = housesFrom;
    },
    SET_MODALVISIABLE(state, modalVisiable) {
      state.modalVisiable = modalVisiable;
    },
    SWITCH_BUSSTATUS(state, busStatus) {
      state.busStatus = busStatus;
    },
    SWITCH_SCHOOLSTATUS(state, schoolStatus) {
      state.schoolStatus = schoolStatus;
    },
    SWITCH_BK9STATUS(state, BK9Status) {
      state.BK9Status = BK9Status;
    },
    SWITCH_PM9STATUS(state, PM9Status) {
      state.PM9Status = PM9Status;
    },
    SWITCH_CE7STATUS(state, CE7Status) {
      state.CE7Status = CE7Status;
    },
    SWITCH_CS2STATUS(state, CS2Status) {
      state.CS2Status = CS2Status;
    },
    SET_BUS_LIST(state, buses) {
      state.buses = buses;
    },
    SET_SCHOOL_LIST(state, schools) {
      state.schools = schools;
    },
    SET_SELECTEDHOUSELAT(state, selectedHouseLat) {
      state.selectedHouseLat = selectedHouseLat;
    },
    SET_SELECTEDHOUSELNG(state, selectedHouseLng) {
      state.selectedHouseLng = selectedHouseLng;
    },
    SET_SELECTEDHOUSENAME(state, selectedHouseName) {
      state.selectedHouseName = selectedHouseName;
    },
    SET_BUS_LIST_TOP3(state, busesTop3) {
      state.busesTop3 = busesTop3;
    },
    SET_SCHOOL_LIST_TOP3(state, schoolsTop3) {
      state.schoolsTop3 = schoolsTop3;
    },
    SET_WEATHER(state, weather) {
      state.weather = weather;
    },
    SET_FAVORITE_CHK(state, favoriteChk) {
      state.favoriteChk = favoriteChk;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
          commit("SET_LATITUDE", 37.5666805);
          commit("SET_LONGITUDE", 126.9784147);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      gugunList(
        sidoCode,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      dongList(
        gugunCode,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = {
        regCode: dongCode,
        year: "2021",
        month: "07",
      };
      houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseListOneByLatLng: ({ commit }, { lat, lng }) => {
      const params = {
        lat: lat,
        lng: lng,
      };
      houseListOneByLatLng(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseListByLatLng: ({ commit }, { lat, lng, apartmentName }) => {
      const params = {
        lat: lat,
        lng: lng,
        apartmentName: apartmentName,
      };
      houseListByLatLng(
        params,
        ({ data }) => {
          commit("SET_HOUSE_PRICE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getBusStation: ({ commit }, { lat, lng }) => {
      const params = {
        lat: lat,
        lng: lng,
      };
      busStationList(
        params,
        ({ data }) => {
          commit("SET_BUS_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSchool: ({ commit }, { lat, lng }) => {
      const params = {
        lat: lat,
        lng: lng,
      };
      schoolList(
        params,
        ({ data }) => {
          commit("SET_SCHOOL_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getBusStationTop3: ({ commit }, { lat, lng }) => {
      const params = {
        lat: lat,
        lng: lng,
      };
      busStationList(
        params,
        ({ data }) => {
          //data에서 맨위3개만 파싱
          let list = [];
          var Obj = function (bus_name, gps_lati, gps_long) {
            this.bus_name = bus_name;
            this.gps_lati = gps_lati;
            this.gps_long = gps_long;
          };
          for (let i = 0; i < data.length; i++) {
            if (i === 3) break;
            list.push(new Obj(data[i].bus_name, data[i].gps_lati, data[i].gps_long));
          }
          // console.log(list);
          commit("SET_BUS_LIST_TOP3", list);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSchoolTop3: ({ commit }, { lat, lng }) => {
      const params = {
        lat: lat,
        lng: lng,
      };
      schoolList(
        params,
        ({ data }) => {
          //data에서 맨위3개만 파싱
          let list = [];
          var Obj = function (schoolName, gpsLati, gpsLong) {
            this.schoolName = schoolName;
            this.gpsLati = gpsLati;
            this.gpsLong = gpsLong;
          };
          for (let i = 0; i < data.length; i++) {
            if (i === 3) break;
            list.push(new Obj(data[i].schoolName, data[i].gpsLati, data[i].gpsLong));
          }
          commit("SET_SCHOOL_LIST_TOP3", list);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    addFavoriteDong: ({ commit }, { userId, dongCode }) => {
      const params = {
        userId: userId,
        dongCode: dongCode,
      };
      // console.log(params);
      addMyDong(
        params,
        ({ data }) => {
          alert("관심 지역을 추가하였습니다.");
          commit("SET_FAVORITE_CHK", true);
        },
        (error) => {
          console.log(error);
          alert("관심 지역 추가에 실패하였습니다.");
        }
      );
    },
    delFavoriteDong: ({ commit }, { userId, dongCode }) => {
      const params = {
        userId: userId,
        dongCode: dongCode,
      };
      // console.log(params);
      delMyDong(
        params,
        ({ data }) => {
          alert("관심 지역을 해제하였습니다.");
          commit("SET_FAVORITE_CHK", false);
        },
        (error) => {
          console.log(error);
          alert("관심 지역 해제에 실패하였습니다.");
        }
      );
    },

    isFavorite: ({ commit }, { userId, dongCode }) => {
      const params = {
        userId: userId,
        dongCode: dongCode,
      };
      // console.log(params);
      isFavorite(
        params,
        ({ data }) => {
          if (data === "success") {
            commit("SET_FAVORITE_CHK", true);
          } else {
            commit("SET_FAVORITE_CHK", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getDongWeather: ({ commit }, { lat, lng }) => {
      const params = {
        lat: lat,
        lon: lng,
      };
      // console.log(params);
      dongWeather(
        params,
        ({ data }) => {
          commit("SET_WEATHER", data);
        },
        (error) => {
          console.error("weather error!", error);
        }
      );
    },
  },
};

export default houseStore;
