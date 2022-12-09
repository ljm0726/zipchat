import { Line } from "vue-chartjs";

export default {
	extends: Line,
	props: ["chartData", "options"],
	// mixins: [reactiveProp],
	// mounted() {
	// 	renderChart(chartData, options);
	// },
};
