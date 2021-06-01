<script>
import { Doughnut } from "vue-chartjs";

export default {
  extends: Doughnut,
  props: ['chartData', 'options'],
  data() {
    return {
      
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