<template>
  <div id="image_url">
    {{image_url}}
  </div>
</template>
<script>

import ChartJsImage from "chartjs-to-image";

export default {
  data() {
    return {
      image_url: null
    };
  },
  async created() {
    const chart = new ChartJsImage();

    chart.setConfig({
  type: 'bar',
  data: { labels: ['Hello world111', 'Foo bar'], datasets: [{ label: 'Foo', data: [1, 2] }] },
});

this.image_url = await chart.getShortUrl();
console.log(this.image_url);
//window.location.href = dataUrl;

  },
  methods: {
  },
};
</script>