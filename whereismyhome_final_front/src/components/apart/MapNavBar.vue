<template>
  <div id="map-nav-bar">
    <div class="dropdownGroup">
      <div class="map-dropdown">
        <fieldset>
          <select
            v-model="sidoCode"
            @change="gugunList"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option v-for="(item, index) in sidos" :key="index" :value="item.value">
              {{ item.text }}
            </option>
          </select>
        </fieldset>
      </div>
      <div class="map-dropdown">
        <fieldset>
          <select
            v-model="gugunCode"
            @change="dongList"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option v-for="(item, index) in guguns" :key="index" :value="item.value">
              {{ item.text }}
            </option>
          </select>
        </fieldset>
      </div>
      <div class="map-dropdown">
        <fieldset>
          <select
            v-model="dongCode"
            @change="searchApt"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option v-for="(item, index) in dongs" :key="index" :value="item.value">
              {{ item.text }}
            </option>
          </select>
        </fieldset>
      </div>
          <div class="favoriteBtnGruop" v-if="!this.hereFavoriteChk">

      <button
        id="favoriteBtn-bf"
        class="w-btn-outline w-btn-skin-outline"
        @click="addFavoiteDong"
      >
        관심지역등록
      </button>
    </div>
    <div class="favoriteBtnGruop" v-else>
      <button
        id="favoriteBtn-af"
        class="w-btn-outline w-btn-skin-outline"
        @click="delFavoiteDong"
      >
        관심지역해제
      </button>
    </div>
    </div>

    <div class="bntGroup">
      <button type="button" class="btn btn-outline-dark" id="busBtn" @click="busActive">
        버스
      </button>
      <button
        type="button"
        class="btn btn-outline-dark"
        id="schoolBtn"
        @click="schoolActive"
      >
        학교
      </button>
      <button
        type="button"
        class="btn btn-outline-dark"
        id="BK9Btn"
        @click="categoryActive('BK9')"
      >
        은행
      </button>
      <button
        type="button"
        class="btn btn-outline-dark"
        id="PM9Btn"
        @click="categoryActive('PM9')"
      >
        약국
      </button>
      <button
        type="button"
        class="btn btn-outline-dark"
        id="CE7Btn"
        @click="categoryActive('CE7')"
      >
        카페
      </button>
      <button
        type="button"
        class="btn btn-outline-dark"
        id="CS2Btn"
        @click="categoryActive('CS2')"
      >
        편의점
      </button>
    </div>
    <div class="weatherGroup">
      <div class="weatherWrapper">
        <b>{{ weather.weather[0].main }}</b> &nbsp; | &nbsp; 현재 온도:
        {{ weather.main.temp }}°C &nbsp; | &nbsp;체감 온도:
        {{ weather.main.feels_like }}°C &nbsp; | &nbsp;습도: {{ weather.main.humidity }}%
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";
const houseStore = "houseStore";
const favoriteStore = "favoriteStore";

export default {
  name: "MapNavBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      isBus: false,
      isSchool: false,
      isBK9: false,
      isPM9: false,
      isCE7: false,
      isCS2: false,
      hereFavoriteChk: false,
    };
  },
  mounted() {
    this.isBus = false;
    this.isSchool = false;
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "busStatus",
      "schoolStatus",
      "BK9Status",
      "PM9Status",
      "CE7Status",
      "CS2Status",
      "weather",
      "favoriteChk",
    ]),
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    favoriteChk: function () {
      const userId = this.userInfo.id;
      const dongCode = this.dongCode;
      this.isFavorite({ userId, dongCode });
      this.hereFavoriteChk = this.favoriteChk;
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    //시 정보 불러오기->rest통신
    this.getSido();

    this.$store.commit("houseStore/SWITCH_BUSSTATUS", false);
    this.$store.commit("houseStore/SWITCH_SCHOOLSTATUS", false);
    this.$store.commit(`houseStore/SWITCH_BK9STATUS`, false);
    this.$store.commit(`houseStore/SWITCH_PM9STATUS`, false);
    this.$store.commit(`houseStore/SWITCH_CE7STATUS`, false);
    this.$store.commit(`houseStore/SWITCH_CS2STATUS`, false);
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "addFavoriteDong",
      "delFavoriteDong",
      "isFavorite",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
      "SWITCH_BUSSTATUS",
      "SWITCH_SCHOOLSTATUS",
      "SWITCH_CATEGORYNAME",
      "SWITCH_BK9STATUS",
      "SWITCH_PM9STATUS",
      "SWITCH_CE7STATUS",
      "SWITCH_CS2STATUS",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      //시도 코드를 보내서 구군 리스트를 불러온다
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },

    searchApt() {
      //동코드로 houese를 바꿔주는 것임
      this.$store.commit("houseStore/SET_HOUSESFROM", 0);
      if (this.dongCode) {
        this.getHouseList(this.dongCode);
        if (this.userInfo != null) {
          const userId = this.userInfo.id;
          const dongCode = this.dongCode;
          this.isFavorite({ userId, dongCode });
          this.hereFavoriteChk = this.favoriteChk;
        }
      }
    },

    busActive() {
      var btn = document.getElementById("busBtn");
      if (this.isBus) {
        this.isBus = false;
        btn.style.backgroundColor = "white";
        btn.style.color = "black";

        //busStatus를 false로
        this.$store.commit("houseStore/SWITCH_BUSSTATUS", false);
      } else {
        this.isBus = true;
        btn.style.backgroundColor = "#212529";
        btn.style.color = "#ffffff";

        //busStatus를 true로
        this.$store.commit("houseStore/SWITCH_BUSSTATUS", true);
      }
    },
    schoolActive() {
      var btn = document.getElementById("schoolBtn");
      if (this.isschool) {
        this.isschool = false;
        btn.style.backgroundColor = "white";
        btn.style.color = "black";

        //schoolStatus를 true로
        this.$store.commit("houseStore/SWITCH_SCHOOLSTATUS", false);
      } else {
        this.isschool = true;
        btn.style.backgroundColor = "#212529";
        btn.style.color = "#ffffff";

        this.$store.commit("houseStore/SWITCH_SCHOOLSTATUS", true);
      }
    },
    categoryActive(str) {
      var btn = "";
      var temp = "";
      if (str === "BK9") {
        temp = this.isBK9;
        btn = document.getElementById("BK9Btn");
      } else if (str === "PM9") {
        temp = this.isPM9;
        btn = document.getElementById("PM9Btn");
      } else if (str === "CE7") {
        temp = this.isCE7;
        btn = document.getElementById("CE7Btn");
      } else if (str === "CS2") {
        temp = this.isCS2;
        btn = document.getElementById("CS2Btn");
      }

      if (temp) {
        temp = false;
        btn.style.backgroundColor = "white";
        btn.style.color = "black";

        if (str === "BK9") {
          this.isBK9 = false;
        } else if (str === "PM9") {
          this.isPM9 = false;
        } else if (str === "CE7") {
          this.isCE7 = false;
        } else if (str === "CS2") {
          this.isCS2 = false;
        }
        this.$store.commit(`houseStore/SWITCH_${str}STATUS`, false);
      } else {
        temp = true;
        btn.style.backgroundColor = "#212529";
        btn.style.color = "#ffffff";

        if (str === "BK9") {
          this.isBK9 = true;
        } else if (str === "PM9") {
          this.isPM9 = true;
        } else if (str === "CE7") {
          this.isCE7 = true;
        } else if (str === "CS2") {
          this.isCS2 = true;
        }
        this.$store.commit(`houseStore/SWITCH_${str}STATUS`, true);
      }
    },
    addFavoiteDong() {
      //관심지역추가
      if (this.userInfo === null) {
        alert("로그인 후 이용 가능합니다.");
        return;
      } else if (this.dongCode === "" || this.dongCode === null) {
        alert("관심 지역 추가 실패! 관심 지역을 선택해주세요.");
      } else {
        var userId = this.userInfo["id"];
        var dongCode = this.dongCode;
        // console.log("add", userId, dongCode);
        this.addFavoriteDong({ userId, dongCode });
      }
    },
    delFavoiteDong() {
      //관심지역제거
      var userId = this.userInfo.id;
      var dongCode = this.dongCode;
      // console.log("DEL ", userId, dongCode);
      this.delFavoriteDong({ userId, dongCode });
    },
  },
};
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700);
#map-nav-bar {
  border-bottom: 1px solid gray;
  height: 60px;
  display: flex;
  align-items: center;
  padding-left: 5px;
}
.map-dropdown {
  margin: 0px 5px;
}
.dropdownGroup {
  width: 36%;
  display: flex;
  align-items: center;
  /* border: 1px solid gray; */
}
.favoriteBtnGruop {
  /* border: 1px solid blue; */
  /* width: 7%; */
  display: flex;
  align-items: center;
  margin-left: 10px;
}
#favoriteBtn {
  border-radius: 60px;
  height: 30px;
  font-size: 1px;
}
#favoriteDelBtn {
  border-radius: 50px;
  height: 30px;
  font-size: 12px;
}

.weatherGroup {
  /* border: 1px solid gray; */
  width: 38%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.weatherWrapper {
  border: 1px solid gray;
  border-radius: 50px;
  position: absolute;
  display: flex;
  align-items: center;
  right: 0;
  height: 4%;
  margin-right: 2%;
  padding-left: 2%;
  padding-right: 2%;
}

.bntGroup {
  width: 28%;
  /* border: 1px solid red; */

  padding: 0px;
}
.bntGroup button {
  margin-right: 2.5px;
  margin-left: 2.5px;
}

/* 버튼 */
.w-btn-outline {
  position: relative;
  padding: 7px 7px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 10px 30px rgba(68, 68, 68, 0.2);
  text-decoration: none;
  font-size: 10px;
  transition: 0.25s;
  background: whitesmoke;
}
#favoriteBtn-bf {
  background-color: whitesmoke;
}
#favoriteBtn-bf:hover {
  background-color: #d4d4d4;
}
#favoriteBtn-af {
  background-color: #d4d4d4;
}
#favoriteBtn-af:hover {
  background-color: whitesmoke;
}
.w-btn-outline:hover {
  letter-spacing: 2px;
  transform: scale(1.1);
  cursor: pointer;
}
.w-btn-outline:hover {
  letter-spacing: 2px;
  transform: scale(1.1);
  cursor: pointer;
}

.w-btn-skin-outline {
  border: 2px solid #d4d4d4;
  color: #6e6e6e;
}
</style>
