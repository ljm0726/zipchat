<template>
  <b-container class="bv-example-row mt-3">
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },

  data() {
    return {};
  },
  created() {},

  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {},
};
</script>

<style></style>
