<template>
  <b-row
    class="m-2"
    @click="selectFavorite"
    @mouseover="colorChange(true), favoriteHouseInfo(favorite)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      <h4>[{{ favorite.dong }}] {{ favorite.apartmentName }}</h4>
      <pre>{{ favorite.area }} m<sup>2</sup>  {{ favorite.floor }}층 <br/>거래금액 : {{
          (parseInt(favorite.dealamount.replace(",", "")) * 10000) | price
        }} 원  <br/> 상세주소: {{ favorite.roadName }} {{ parseInt(favorite.bonbun) }}
        </pre>
    </b-col>
  </b-row>
</template>

<script>

export default {
  name: "FavoriteListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    favorite: Object,
  },
  methods: {

    selectFavorite() {
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    favoriteHouseInfo(favorite) {
      // console.log(favorite);
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
