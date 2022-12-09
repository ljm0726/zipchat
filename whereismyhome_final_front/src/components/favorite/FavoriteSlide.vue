<template>
	<div>
		<b-carousel
			v-if="mainFavoriteList"
			id="carousel-1"
			v-model="slide"
			:interval="3000"
			indicators
			background="#ababab"
			img-width="1024"
			img-height="480"
			style="text-shadow: 1px 1px 2px #333"
			@sliding-start="onSlideStart"
			@sliding-end="onSlideEnd"
		>
			<!-- Text slides with image -->
			<b-carousel-slide
				v-for="(abc, index) in mainFavoriteList"
				:key="index"
				caption=""
				img-src="https://picsum.photos/1024/480/?image=52"
				><div>{{ abc[0].apartmentName }}</div>
				{{ abc[0].area }} m<sup>2</sup> &nbsp; {{ abc[0].floor }} 층
				<div>{{ parseInt(abc[0].dealamount.replace(",", "")) | price }} 만원</div>
			</b-carousel-slide>
		</b-carousel>

		<p class="mt-4">
			Slide #: {{ slide }}<br />
			Sliding: {{ sliding }}
		</p>
	</div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";
const favoriteStore = "favoriteStore";
const houseStore = "houseStore";

export default {
	data() {
		return {
			slide: 0,
			sliding: null,
		};
	},
	computed: {
		...mapState(memberStore, ["userInfo"]),
		...mapState(favoriteStore, ["mainFavoriteList"]),
	},
	created() {
		if (this.userInfo != null) {
			this.getMainFavoriteList(this.userInfo.id);
		}
	},
	mounted() {
		this.getDongWeather(this.dongLoc.lat, this.dongLoc.lng);

		console.log(this.weather);
	},
	methods: {
		...mapActions(favoriteStore, ["getMainFavoriteList"]),
		onSlideStart(slide) {
			this.sliding = true;
		},
		onSlideEnd(slide) {
			this.sliding = false;
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
