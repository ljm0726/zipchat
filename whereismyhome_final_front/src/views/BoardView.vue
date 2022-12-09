<template>
  <div class="home">
    <b-container class="bv-example-row mt-5 text-center">
      <router-view></router-view>
    </b-container>
  </div>
</template>
<script>
export default {
  name: "BoardView",
};
</script>
<style scoped>
.home {
  height: 100vh;
}
</style>
