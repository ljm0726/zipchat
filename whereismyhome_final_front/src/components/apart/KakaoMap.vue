<template>
  <div id="mapWrapper">
    <div id="map"></div>
    <kakao-map-modal></kakao-map-modal>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import KakaoMapModal from "@/components/apart/KakaoMapModal.vue";
const houseStore = "houseStore";
export default {
  name: "KakaoMap",
  components: {
    KakaoMapModal,
  },
  data() {
    return {
      map: "",
      markers: [], // 건물 마커 객체를 가지고 있을 배열
      busMarkers: [],
      schoolMarkers: [],
      BK9Markers: [],
      PM9Markers: [],
      CE7Markers: [],
      CS2Markers: [],
      ps: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "houses",
      "latitude",
      "longitude",
      "housesFrom",
      "buses",
      "schools",
      "busStatus",
      "schoolStatus",
      "BK9Status",
      "PM9Status",
      "CE7Status",
      "CS2Status",
    ]),
  },
  watch: {
    latitude: function () {
      //위도가 바뀌면  -> houses갱신 -> 마커도 다시 변경

      //드래그로 houses값을 바꾸는 것임을 알려줌. 1=드래그
      this.$store.commit("houseStore/SET_HOUSESFROM", 1);

      //위도경도를 전달한다
      this.searchAptOneByLatLng(this.latitude, this.longitude);

      //위도가 바뀌면 버스 정류장도 갱신
      this.searchBusStation(this.latitude, this.longitude);

      //위도가 바뀌면 학교도 갱신
      this.searchSchool(this.latitude, this.longitude);

      //날씨 갱신
      this.searchWeather(this.latitude, this.longitude);

      //카테고리 장소들 검색
      this.searchPlaces();
    },
    // longitude: function(){
    // },
    houses: function () {
      //houses리스트 변화가 감지되면 맵에 다시 뿌려준다
      this.displayMarkers(this.houses); //마커 뿌리기
    },
    buses: function () {
      //buses리스트 변화가 감지되면 맵에 다시 뿌려준다
      //단, busStatus가 true일때만.
      if (this.busStatus) {
        this.displayBusMarkers(this.buses);
      }
    },
    schools: function () {
      if (this.schoolStatus) {
        this.displaySchoolMarkers(this.schools);
      }
    },
    busStatus: function () {
      if (this.busStatus) {
        //this.busStatus===true
        this.displayBusMarkers(this.buses);
      } else {
        this.deleteMarkers(this.busMarkers);
      }
    },
    schoolStatus: function () {
      if (this.schoolStatus) {
        this.displaySchoolMarkers(this.schools);
      } else {
        this.deleteMarkers(this.schoolMarkers);
      }
    },
    BK9Status: function () {
      if (this.BK9Status) {
        this.ps.categorySearch("BK9", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.BK9Markers);
      }
    },
    PM9Status: function () {
      if (this.PM9Status) {
        this.ps.categorySearch("PM9", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.PM9Markers);
      }
    },
    CE7Status: function () {
      if (this.CE7Status) {
        this.ps.categorySearch("CE7", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.CE7Markers);
      }
    },
    CS2Status: function () {
      if (this.CS2Status) {
        this.ps.categorySearch("CS2", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.CS2Markers);
      }
    },
  },
  mounted() {
    const appkey = process.env.VUE_APP_KAKAOMAP_KEY;
    //카카오맵 불러오기
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${appkey}&libraries=services`;
      document.head.appendChild(script);
    }

    this.$store.commit("houseStore/SWITCH_BUSSTATUS", false);
    this.$store.commit("houseStore/SWITCH_SCHOOLSTATUS", false);
  },
  methods: {
    ...mapActions(houseStore, [
      "getHouseListOneByLatLng",
      "getHouseListByLatLng",
      "getBusStation",
      "getSchool",
      "getDongWeather",
    ]),
    ...mapMutations(houseStore, [
      "SET_LATITUDE",
      "SET_LONGITUDE",
      "SET_HOUSESFROM",
      "SWITCH_BUSSTATUS",
      "SWITCH_SCHOOLSTATUS",
      "SET_SELECTEDHOUSELAT",
      "SET_SELECTEDHOUSELNG",
      "SET_SELECTEDHOUSENAME",
      "SWITCH_WEATHER",
    ]),
    initMap() {
      const container = document.querySelector("#map");
      const options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };

      this.map = new kakao.maps.Map(container, options);

      this.$store.commit("houseStore/SET_MODALVISIABLE", false);


      let base = this;
      kakao.maps.event.addListener(this.map, "idle", function () {
        // 지도의  레벨을 얻어옵니다
        // var level = base.map.getLevel();

        // 지도의 중심좌표를 얻어옵니다
        var latlng = base.map.getCenter();

        //얻어온 중심좌표를 store의 state에 전달
        base.updateLatLng(latlng);
      });

      kakao.maps.event.addListener(this.map, "click", function () {
        //모달 없애기
        base.$store.commit("houseStore/SET_MODALVISIABLE", false);
      });

      //편의시설
      this.ps = new kakao.maps.services.Places(map);
    },
    deleteMarkers(markerList) {
      //지도 위에 있는 마커삭제
      markerList.forEach((m) => m.setMap(null)); //지도 위에서 마커 안 보이게
      markerList = []; //해당 마커 배열 초기화
    },
    displayMarkers(list) {
      //기존 마커 삭제
      this.deleteMarkers(this.markers);

      //houses정보로 마커생성 후 지도에 뿌리기
      //동 코드로 houese를 바꾸면 -> setBound를 쓴다
      if (this.housesFrom === 0) {
        let bounds = new kakao.maps.LatLngBounds();
        if (list.length !== 0) {
          list.forEach(({ lat, lng, apartmentName }, index) => {
            let markerLocation = new kakao.maps.LatLng(lat, lng);
            this.makeMarker(markerLocation, apartmentName, index);
            bounds.extend(markerLocation);
          });
          this.map.setBounds(bounds);
        } else {
          // alert("데이터가 없습니다.");
        }
      } else {
        //동 코드로 houese를 바꾸면 -> setBound를 쓰지 않는다
        if (list.length !== 0) {
          list.forEach(({ lat, lng, apartmentName }, index) => {
            let markerLocation = new kakao.maps.LatLng(lat, lng);
            this.makeMarker(markerLocation, apartmentName, index);
          });
        } else {
          // alert("데이터가 없습니다.");
        }
      }
    },
    makeMarker(markerLocation, apartmentName, index) {
      //markers에 건물 마커를 생성
      const houseImgSrc = require("../../assets/house_icon5.png");
      const imgSize = new kakao.maps.Size(35, 35);
      const houseImg = new kakao.maps.MarkerImage(houseImgSrc, imgSize);

      let marker = new kakao.maps.Marker({
        position: markerLocation,
        image: houseImg,
      });
      this.markers.push(marker);
      marker.setMap(this.map);

      //마커 클릭 이벤트를 등록합니다.
      let base = this;
      kakao.maps.event.addListener(marker, "click", () => {
        //위도경도 아파트이름으로 세부 거래내역 검색 후 모달에 전달
        this.searchAptByLatLng(markerLocation["Ma"], markerLocation["La"], apartmentName);

        //선택한 아파트의 위도 경도값 갱신
        this.$store.commit("houseStore/SET_SELECTEDHOUSELAT", markerLocation["Ma"]);
        this.$store.commit("houseStore/SET_SELECTEDHOUSELNG", markerLocation["La"]);
        this.$store.commit("houseStore/SET_SELECTEDHOUSENAME", apartmentName);
        //modalVisiable = true;
        this.$store.commit("houseStore/SET_MODALVISIABLE", true);
      });

      //커스텀레이아웃말풍선
      var content_apart = `<div style="padding: 10px 15px;
      position: relative; bottom:20px; border-radius: 28px; background-color: black;
      box-shadow: rgb(0 0 0 / 4%) 0px 0px 0px 1px, rgb(0 0 0 / 18%) 0px 2px 4px;
      color: rgb(255, 255, 255); text-align:center;
      font-size: 14px;
      font-weight: 880;">${apartmentName}</div>`;

      var customOverlay = new kakao.maps.CustomOverlay({
        // map: this.map,
        position: markerLocation,
        content: content_apart,
        yAnchor: 1.7,
      });
      //마우스를 올렸을 때 -> 아파트 이름 보이게
      kakao.maps.event.addListener(marker, "mouseover", () => {
        customOverlay.setMap(this.map, marker);
      });
      //인포 사라짐
      kakao.maps.event.addListener(marker, "mouseout", function () {
        customOverlay.setMap(null);
      });
      kakao.maps.event.addListener(this.map, "zoom_changed", function () {
        customOverlay.setMap(null);
      });
      kakao.maps.event.addListener(this.map, "idle", function () {
        customOverlay.setMap(null);
      });
      kakao.maps.event.addListener(this.map, "click", function () {
        customOverlay.setMap(null);
      });
    },
    updateLatLng(latlng) {
      this.$store.commit("houseStore/SET_LATITUDE", latlng["Ma"]);
      this.$store.commit("houseStore/SET_LONGITUDE", latlng["La"]);
    },
    searchAptOneByLatLng(lat, lng) {
      //거래내역 여러개여도 아파트만 띄움
      if ((lat, lng)) this.getHouseListOneByLatLng({ lat, lng });
    },
    searchAptByLatLng(lat, lng, apartmentName) {
      //아파트의 모든 거래내역
      if ((lat, lng, apartmentName))
        this.getHouseListByLatLng({ lat, lng, apartmentName });
    },
    searchBusStation(lat, lng) {
      if ((lat, lng)) this.getBusStation({ lat, lng });
    },
    searchSchool(lat, lng) {
      if ((lat, lng)) this.getSchool({ lat, lng });
    },
    searchWeather(lat, lng) {
      if ((lat, lng)) this.getDongWeather({ lat, lng });
    },

    /*BUS마커 관리*/
    displayBusMarkers(list) {
      this.deleteMarkers(this.busMarkers);
      //buses정보로 마커생성 후 지도에 뿌리기
      if (list.length !== 0) {
        list.forEach(({ gps_lati, gps_long, bus_name }, index) => {
          let markerLocation = new kakao.maps.LatLng(gps_lati, gps_long);
          this.makeBusMarker(markerLocation, bus_name, index);
        });
      }
    },
    displaySchoolMarkers(list) {
      this.deleteMarkers(this.schoolMarkers);
      //schools정보로 마커생성 후 지도에 뿌리기
      if (list.length !== 0) {
        list.forEach(({ gpsLati, gpsLong, schoolName }, index) => {
          let markerLocation = new kakao.maps.LatLng(gpsLati, gpsLong);
          this.makeSchoolMarker(markerLocation, schoolName, index);
        });
      }
    },
    makeBusMarker(markerLocation, bus_name, index) {
      const busImgSrc = require("../../assets/bus_icon.png");
      const imgSize = new kakao.maps.Size(20, 20);
      const busImg = new kakao.maps.MarkerImage(busImgSrc, imgSize);
      let marker = new kakao.maps.Marker({
        position: markerLocation,
        image: busImg,
      });
      this.busMarkers.push(marker);
      marker.setMap(this.map);

      //마커 클릭 이벤트를 등록합니다.
      let base = this;
      kakao.maps.event.addListener(marker, "click", () => {
        customOverlay.setMap(null);
      });

      //커스텀레이아웃말풍선
      var content_bus = `<div style="padding: 10px 15px;
      position: relative; bottom:20px; border-radius: 28px; background-color: black;
      box-shadow: rgb(0 0 0 / 4%) 0px 0px 0px 1px, rgb(0 0 0 / 18%) 0px 2px 4px;
      color: rgb(255, 255, 255); text-align:center;
      font-size: 14px;
      font-weight: 880;">${bus_name}</div>`;

      var customOverlay = new kakao.maps.CustomOverlay({
        // map: this.map,
        position: markerLocation,
        content: content_bus,
        yAnchor: 1.5,
      });

      //마우스를 올렸을 때 -> 버스정류장 보이게
      kakao.maps.event.addListener(marker, "mouseover", () => {
        customOverlay.setMap(this.map, marker);
      });
      // 마우스 아웃 -> 인포 사라짐
      kakao.maps.event.addListener(marker, "mouseout", function () {
        customOverlay.setMap(null);
      });
    },
    makeSchoolMarker(markerLocation, schoolName, index) {
      const schoolImgSrc = require("../../assets/school_icon.png");
      const imgSize = new kakao.maps.Size(30, 30);
      const schoolImg = new kakao.maps.MarkerImage(schoolImgSrc, imgSize);

      let marker = new kakao.maps.Marker({
        position: markerLocation,
        image: schoolImg,
      });
      this.schoolMarkers.push(marker);
      marker.setMap(this.map);

      //마커 클릭 이벤트를 등록합니다.
      let base = this;
      kakao.maps.event.addListener(marker, "click", () => {
        customOverlay.setMap(null);
      });

      //커스텀레이아웃말풍선
      var content_school = `<div style="padding: 10px 15px;
      position: relative; bottom:20px; border-radius: 28px; background-color: black;
      box-shadow: rgb(0 0 0 / 4%) 0px 0px 0px 1px, rgb(0 0 0 / 18%) 0px 2px 4px;
      color: rgb(255, 255, 255); text-align:center;
      font-size: 14px;
      font-weight: 880;">${schoolName}</div>`;

      var customOverlay = new kakao.maps.CustomOverlay({
        // map: this.map,
        position: markerLocation,
        content: content_school,
        yAnchor: 1.5,
      });
      //마우스를 올렸을 때 -> 학교 보이게
      kakao.maps.event.addListener(marker, "mouseover", () => {
        customOverlay.setMap(this.map, marker);
      });
      // 마우스 아웃 -> 인포 사라짐
      kakao.maps.event.addListener(marker, "mouseout", function () {
        customOverlay.setMap(null);
      });
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      //각 status검사
      if (this.BK9Status) {
        this.deleteMarkers(this.BK9Markers);
        this.ps.categorySearch("BK9", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.BK9Markers);
      }

      if (this.PM9Status) {
        this.deleteMarkers(this.PM9Markers);
        this.ps.categorySearch("PM9", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.PM9Markers);
      }

      if (this.CE7Status) {
        this.deleteMarkers(this.CE7Markers);
        this.ps.categorySearch("CE7", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.CE7Markers);
      }

      if (this.CS2Status) {
        this.deleteMarkers(this.CS2Markers);
        this.ps.categorySearch("CS2", this.placesSearchCB, {
          location: new kakao.maps.LatLng(this.latitude, this.longitude),
        });
      } else {
        this.deleteMarkers(this.CS2Markers);
      }
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        console.log("검색 결과 없음");
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        console.log("에러..");
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        this.displayCategoryMarkers(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          places[i].category_group_code
        );

      }
    },
    displayCategoryMarkers(position, str) {
      const imageSrc = require(`../../assets/${str}_icon.png`);
      const imgSize = new kakao.maps.Size(20, 20);
      const markerImg = new kakao.maps.MarkerImage(imageSrc, imgSize);
      let marker = new kakao.maps.Marker({
        position: position,
        image: markerImg,
      });
      if (str === "BK9") {
        this.BK9Markers.push(marker);
      } else if (str === "PM9") {
        this.PM9Markers.push(marker);
      } else if (str === "CE7") {
        this.CE7Markers.push(marker);
      } else if (str === "CS2") {
        this.CS2Markers.push(marker);
      }

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
    },
  },
};
</script>

<style scoped>
#mapWrapper {
  position: relative;
}
#map {
  width: 100%;
  height: 85vh;
}
</style>
