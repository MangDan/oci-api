<script>
import { Doughnut } from "vue-chartjs";

export default {
  extends: Doughnut,
  props: ['chartData', 'options'],
  data() {
    console.log("11111");
    console.log(this.chartData);
    console.log(this.options);
    console.log("22222");
    
    return {
      
      // chartData: {
      //   labels: ["Critical", "High", "Medium", "Low", "Minor"],
      //   datasets: [
      //     {
      //       backgroundColor: [
      //         "rgb(237, 94, 87)",
      //         "rgb(240, 132, 69)",
      //         "rgb(251, 209, 85)",
      //         "rgb(3, 190, 187)",
      //         "rgb(44, 156, 217)",
      //       ],
      //       data: [1000, 500, 1500, 1000, 111],
      //     },
      //   ],
      // },
      // options: {
      //   legend: {
      //     display: true,
      //     position: "right",
      //   },
      //   responsive: false,
      //   maintainAspectRatio: false,
      //   cutoutPercentage: 70,
      //   height: 170,
      //   centerText: {
      //     text: "3695",
      //   },
      // },
    };
  },
  mounted() {
    this.addPlugin({
      id: "doughnut-center-label-plugin",
      afterDraw: function (chart) {
        var width = chart.chart.width,
          height = chart.chart.height,
          ctx = chart.chart.ctx;

          ctx.restore();
          var fontSize = (height / 100).toFixed(2);
          ctx.font = "bold " + fontSize + "em sans-serif";
          ctx.textBaseline = "middle";

          var text1 = chart.options.centerText.text,
          textX = Math.round(((width - ctx.measureText(text1).width) / 2)-60),
          textY = height / 2;

          ctx.fillText(text1, textX, textY);

          var text2 = "Total";
          textX = Math.round(((width - ctx.measureText(text2).width) / 2)-45),
          textY = (height / 2)+20;
          fontSize = (height / 200).toFixed(2);
          ctx.font = fontSize + "em sans-serif";
          ctx.textBaseline = "middle";

          ctx.fillText(text2, textX, textY);
          ctx.save();
      },
    });

    this.renderChart(this.chartData, this.options);
  }
};
</script>