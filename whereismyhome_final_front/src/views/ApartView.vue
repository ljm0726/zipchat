<template>
  <div class="home">
    <map-nav-bar></map-nav-bar>
    <kakao-map></kakao-map>
  </div>
</template>
<script>
import MapNavBar from "@/components/apart/MapNavBar.vue";
import KakaoMap from "@/components/apart/KakaoMap.vue";
export default {
  name: "ApartView",
  components: {
    MapNavBar,
    KakaoMap,
  },
};
</script>

<style scoped>
.home {
  
}

</style>
