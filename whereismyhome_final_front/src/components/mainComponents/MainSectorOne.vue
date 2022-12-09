<template>
  <div>
    <div
      class="home d-flex flex-column justify-content-center align-items-center"
    >
      <div class="row">
        <div class="mainTitleSmall">
          실거래가 비교를 통해 시세를 알아보세요!
        </div>
      </div>
      <div class="mb-5 mainTitleBig">Where Is My Home?</div>
      <router-link to="/apt"
        ><button class="btn btn-primary btn-ghost btn-through">M O R E</button>
      </router-link>

      <div class="arrowIconWrapper">
        <img
          src="../../assets/arrow_icon.png"
          class="arrowIcon"
          @click="moveNext"
        />
      </div>
    </div>
    <div class="home2" ref="nextSection">
      <div class="row m-5">
        <div class="sectionTitle">
          <h1>News & Notice</h1>
        </div>
        <h6>실시간 부동산 관련 뉴스와 공지사항을 알려드립니다.</h6>
      </div>
      <b-container class="bv-example-row">
        <b-row>
          <b-col class="text-center">
            <h3>News</h3>
            <div class="news-contents-wrapper">
              <news-list></news-list>
            </div>
          </b-col>
          <b-col class="text-center">
            <h3>Notice</h3>
            <div class="news-contents-wrapper">
              <div class="boardListItem">
                <board-list-item
                  v-for="(article, index) in boardList.slice(0, 5)"
                  :key="index"
                  v-bind="article"
                />
              </div>
            </div>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import BoardListItem from "@/components/board/item/BoardListItem";
import NewsList from "@/components/news/NewsList.vue";
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
const newsStore = "newsStore";
const boardStore = "boardStore";

export default {
  name: "MainSectorOne",
  components: {
    NewsList,
    BoardListItem,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(newsStore, ["keyword"]),
    ...mapState(boardStore, ["boardList"]),
  },

  created() {
    this.$store.commit("newsStore/SET_KEYWORD", { dong: "부동산" });
    this.getListArticle();
  },
  methods: {
    ...mapActions(boardStore, ["getListArticle"]),

    moveNext() {
      this.$refs.nextSection.scrollIntoView({
        behavior: "smooth",
        block: "end",
      });
    },
  },
};
</script>

<style scoped>
.home {
  height: 95vh;
  background-image: url("../../assets/mainImg.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
.mainTitleSmall {
  color: #ffffff;
  font-size: 1.5rem;
  font-style: italic;
  font-family: 'PuradakGentleGothicR';
}
.mainTitleBig {
  color: #ffffff;
  font-size: 5rem;
  font-family: 'SANGJUGyeongcheonIsland';
}
.arrowIconWrapper {
  margin-top: 100px;
  animation: bounce 1s linear infinite forwards; /* 애니메이션 적용 */
}
.arrowIcon {
  width: 100px;
  height: 100px;
  cursor: pointer;
}
.home2 {
  height: 93vh;
  padding: 8%;
  background-color: #ffffff;
}
.sectionTitle h1 {
  text-align: center;
  font-size: 30px;
  font-weight: 300;
  color: #222;
  letter-spacing: 1px;
  text-transform: uppercase;

  display: grid;
  grid-template-columns: 1fr max-content 1fr;
  grid-template-rows: 27px 0;
  grid-gap: 20px;
  align-items: center;
}

.sectionTitle h1:after,
.sectionTitle h1:before {
  content: " ";
  display: block;
  border-bottom: 1px solid #c50000;
  border-top: 1px solid #c50000;
  height: 5px;
  background-color: #f8f8f8;
}
.boardListItem {
  width: 560px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.news-contents-wrapper {
  border: 1px solid gray;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
}
/*버튼 */

.btn-through {
  width: 150px;
  height: 50px;
  transition: 0.6s cubic-bezier(0.77, 0, 0.175, 1);
  background-color: #00012095;
  color: #fdfdfd;
  border: 1px solid #4e4e4e;
  border-radius: 24px;
}

.btn-through::before {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  z-index: -1;
  width: 100%;
  height: 100%;
  background: #ffffff;
  transform: scaleX(0);
  transform-origin: right;
  transition: transform 0.6s cubic-bezier(0.77, 0, 0.175, 1);
}
.btn-through:hover {
  color: rgb(0, 0, 0);
  background-color: #000120;
  border: 1px solid #4e4e4e;
  color: #fdfdfd;
}
.btn-through:hover::before {
  transform: scaleX(1);
  transform-origin: left;
}

@keyframes bounce {
  0% {
    transform: translate(0, 0);
  }
  50% {
    transform: translate(0px, 30px);
  }
  100% {
    transform: translate(0, 0);
  }
}
</style>
