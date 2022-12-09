<template>
  <div class="home">
    <main-sector-one />
    <main-sector-two />
    <the-footer />
  </div>
</template>

<script>
import MainSectorOne from "@/components/mainComponents/MainSectorOne.vue";
import MainSectorTwo from "@/components/mainComponents/MainSectorTwo.vue";
import TheFooter from "@/components/TheFooter.vue";
export default {
  name: "MainView",
  components: {
    MainSectorOne,
    MainSectorTwo,
    TheFooter,
  },
};
</script>

<style scoped></style>
