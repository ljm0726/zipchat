<template>
  <div
    class="bg-dark justify-content-center align-items-center d-flex"
    id="footer"
  >
    <div class="footer-wrapper">
      <div class="footer-ssafy">SSAFY</div>
      <div class="footer-contents">
        <div>
          developer | 이종민 l6778@naver.com | 최희경 gmlrud9701@naver.com
        </div>
        <div>SSAFY 8기 서울 15반</div>
        <div>Copyright © 2022 SSAFY All rights reserved.</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "TheFooter",
};
</script>
<style scoped>
#footer {
  height: 120px;
}
.footer-wrapper {
  width: 100%;
  height: 60%;
  display: flex;
  flex-direction: row;
}
.footer-ssafy {
  width: 20%;
  border-right: 1px solid rgb(172, 170, 170);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 30px;
  font-weight: bold;
  color: floralwhite;
}

.footer-contents {
  width: 80%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-end;
  padding-left: 2%;
  font-size: 12px;
  color: rgb(187, 187, 187);
}
</style>
