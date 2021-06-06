<template>
  <div>
    <AggregatedProblems
      v-if="chartData.datasets[0].data.length"
      :width="300"
      :height="170"
      :chart-data="chartData"
      :options="options"
    ></AggregatedProblems>
  </div>
</template>

<script>
import AggregatedProblems from "@/components/AggregatedProblems";

export default {
  components: {
    AggregatedProblems,
  },
  data() {
    return {
      chartData: {
        labels: [],
        datasets: [
          {
            backgroundColor: [
              "rgb(237, 94, 87)",
              "rgb(240, 132, 69)",
              "rgb(251, 209, 85)",
              "rgb(3, 190, 187)",
              "rgb(44, 156, 217)",
            ],
            data: [],
          },
        ],
      },
      options: {
        legend: {
          display: true,
          position: "right",
        },
        responsive: false,
        maintainAspectRatio: false,
        cutoutPercentage: 70,
        height: 170,
        centerText: {
          text: "1",
        },
        animation: {
          animateRotate: false,
          // onComplete: function () {
          //   var image = this.toBase64Image();
          // },
        },
      },
      base64Img: null
    };
  },
  created() {
    this.fillData();
  },
  methods: {
    fillData() {
      let compartment = this.$route.query.compartment
        ? this.$route.query.compartment
        : "gambas";
      this.$axios
        .get(
          "/aggregation/problems?compartment_id=" +
            process.env["VUE_APP_COMPARTMENT_" + compartment]
        )
        .then((result) => {
          this.chartData.labels = Object.keys(result.data);
          this.chartData.datasets[0].data = Object.values(result.data);
          this.options.centerText.text = Object.values(result.data).reduce(
            (a, b) => Number(a) + Number(b),
            0
          );
        });
    },
  },
};
</script>