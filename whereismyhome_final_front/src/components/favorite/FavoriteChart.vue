<template>
	<div v-if="this.favoriteDong != null">
		<line-chart
			ref="lineChart"
			:chartData="chart.data"
			:options="chart.options"
			id="chart"
		></line-chart>
	</div>
	<div v-else>
		<h1>sfasfsafsfsd</h1>
	</div>
</template>

<script>
import { Chart, registerables } from "chart.js";


import LineChart from "@/components/favorite/item/LineChart";

import { mapState, mapActions } from "vuex";
const favoriteStore = "favoriteStore";


export default {
	name: "FavoriteChart",
	type: "line",
	created() {
		Chart.register(...registerables);
	},
	components: {
		LineChart,
	},

	computed: {
		...mapState(favoriteStore, ["favoriteDong", "favoriteDongDealList"]),
	},
	methods: {
		
	},

	watch: {
		favoriteDong: function () {
			Chart.register(...registerables);
		},
		favoriteDongDealList: function () {
			
			setTimeout(() => {
				// Code here
				if (this.favoriteDongDealList) {
					this.chart.data.datasets[0].data = this.favoriteDongDealList;
				} else {
					this.chart.data.datasets[0].data = null;
				}
			}, 200);
		},
	},

	data() {
		return {
			chartData: null,
			options: null,
			chart: {
				data: {
					labels: [2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022.7],
					datasets: [
						{
							label: "거래횟수",
							backgroundColor: "rgb(0, 174, 238, 0.2)",
							pointBackgroundColor: "#00aeee",
							fill: true,
							tension: 0.1,
							borderColor: "#00aeee",
							pointBorderColor: "#00aeee",
							borderWidth: 1,
							pointBorderWidth: 1,
							data: this.favoriteDongDealList,
						},
					],
				},
				options: {
					plugins: {
						legend: {
							display: true,
							position: "left",
							labels: {
								boxWidth: 8,
								padding: 10,
								usePointStyle: true,
								pointStyle: "circle",
								font: {
									size: 14,
								},
							},
							fullSize: false,
							align: "center",
						},
						tooltip: {
							boxWidth: 15,
							bodyFont: {
								size: 14,
							},
						},
					},
					scales: {
						x: {
							grid: {
								display: false,
							},
							ticks: {
								padding: 3,
							},
						},
						y: {
							ticks: {
								callback: (val, index) => {
									return val !== 0 ? val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") : "";
								},
								padding: 10,
							},
							grid: {
								drawBorder: false,
								color: "#00aeee",
								lineWidth: 1,
							},
						},
					},
					responsive: true,
					maintainAspectRatio: false,
					animation: {
						duration: 5000,
					},
				},
			},
		};
	},

};
</script>

<style scoped></style>
