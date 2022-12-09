<template>
  <div class="boardListItemWrapper">
    <b-tr>
      <div>
        <b-td>✔ &nbsp; &nbsp; </b-td>
        <b-th>
          <router-link
            class="noticeTitle"
            :to="{ name: 'boardview', params: { articleNo: articleNo } }"
            >{{ subject }}</router-link
          >
        </b-th>
      </div>
    </b-tr>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleNo: Number,
    userId: String,
    subject: String,
    hit: Number,
    registerTime: String,
  },
  filters: {
    dateFormat(regtime) {
      const today = moment().format("YYYY-MM-DD");
      const regtime2 = moment(new Date(regtime)).format("YYYY-MM-DD");

      if (moment(today).isAfter(regtime2)) {
        //today가 작성일보다 이후면
        return moment(new Date(regtime)).format("YY.MM.DD");
      }
      return moment(new Date(regtime)).format("HH:mm");
    },
  },
};
</script>

<style>
.boardListItemWrapper {
  border-bottom: 1px solid gray;
  border-top: 1px solid gray;
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  padding-left: 50px;
  margin-bottom: 1px;
  margin-top: 1px;
  background-color: rgba(237, 245, 250, 0.438);
}
.noticeTitle {
  text-decoration: none;
  color: rgb(107, 107, 107);
}
.noticeTitle:hover {
  color: black;
}
.boardListItemWrapper:hover {
  background-color: rgb(214, 227, 231);
}
</style>
