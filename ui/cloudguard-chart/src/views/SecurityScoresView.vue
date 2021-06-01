<template>
  <div>
    <SecurityScores
    ></SecurityScores>
  </div>
</template>

<script>
import SecurityScores from "@/components/SecurityScores";

export default {
  components: {
    SecurityScores,
  },
  data() {
    return {
      chartData: {
      },
      options: {
      }
    };
  },
  created() {
  },
  methods: {
  },
};
</script>