<template>
  <b-container
    v-if="favoriteList && favoriteList.length != 0"
    class="bv-example-row mt-3"
  >
    <div>
      <b-table
        id="my-table"
        :items="favoriteList"
        :per-page="perPage"
        :current-page="currentPage"
        :fields="fields"
        small
        stacked
      >
        <b-tbody>
          <b-tr>
            <b-th rowspan="3">Belgium</b-th>
          </b-tr>
        </b-tbody>
        <template #cell(area)="data">
          {{ data.item.area }} m<sup>2</sup>
        </template>
        <template #cell(floor)="data"> {{ data.item.floor }} 층 </template>
        <template #cell(dealamount)="data">
          {{ parseInt(data.item.dealamount.replace(",", "")) | price }} 만원
        </template>
        <template #cell(roadName)="data">
          {{ data.item.roadName }} {{ parseInt(data.item.bonbun) }}
        </template>
      </b-table>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="favoriteList"
        class="d-flex justify-content-center"
      >
      </b-pagination>
    </div>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col class="noHouse">주택 목록이 없습니다.</b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
const favoriteStore = "favoriteStore";

export default {
  name: "FavoriteList",
  components: {},
  data() {
    return {
      fields: [
        { key: "apartmentName", label: "아파트 이름" },
        { key: "area", label: "면적" },
        { key: "floor", label: "층" },
        { key: "dealamount", label: "거래금액" },
        { key: "roadName", label: "주소" },
      ],

      dong: null,
      currentPage: 1,
      perPage: 5,

    };
  },
  created() {
    this.dong = this.favoriteDong;
    

    this.CLEAR_FAVORITE_DONG_LIST();
    this.getFavoriteList(this.favoriteDong);
    
  },

  computed: {
    ...mapState(favoriteStore, ["favoriteList", "favoriteDong"]),
    rows() {
      return this.favoriteList.length;
    },
  },

  watch: {
    favoriteDong: function () {
      this.getFavoriteList(this.favoriteDong);
      
    },
  },

  methods: {
    ...mapActions(favoriteStore, ["getFavoriteList"]),
    ...mapMutations(favoriteStore, ["CLEAR_FAVORITE_DONG_LIST"]),

  },

  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scope>
.noHouse {
  border: 1px solid rgb(88, 88, 88);
  border-radius: 10px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
