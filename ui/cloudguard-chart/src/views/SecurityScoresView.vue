<template>
  <div>
    <v-container fluid style="width: 500px">
      <v-row dense>
        <v-col cols="6">
          <v-card class="mx-auto" color="#5F0289" dark max-width="350" height="150">
            <v-card-title>
              <span
                style="
                  font-size: 1.2rem;
                  font-weight: 400;
                  color: #ffffff;
                  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
                "
                >Security Score Rating</span
              >
            </v-card-title>
            <v-card-subtitle
              style="font-size: 14px; color: #ffffff; font-family: Arial"
              class="text-h3 font-weight-bold pb-0 pt-3"
            >
              {{securityRating}}
            </v-card-subtitle>
            <v-card-text class="body-1 white--text font-weight-bold pb-8">
              <div>{{ securityScore }}</div>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="6">
          <v-card class="mx-auto" color="#243141" dark max-width="250" height="150">
            <v-card-title>
              <span
                style="
                  font-size: 1.2rem;
                  font-weight: 400;
                  color: #ffffff;
                  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
                "
                >Risk Score</span
              >
            </v-card-title>
            <v-card-subtitle
              style="font-size: 14px; color: #ffffff; font-family: Arial"
              class="text-h3 font-weight-bold pb-0 pt-3"
            >
              {{riskScore}}
            </v-card-subtitle>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
//import SecurityScores from "@/components/SecurityScores";

export default {
  components: {
    //SecurityScores,
  },
  data() {
    return {
      securityScore: null,
      riskScore: null,
      securityRating: null
    };
  },
  created() {
    this.fillData();
  },
  methods: {
    fillData() {
      let tenancy = this.$route.query.tenancy
        ? this.$route.query.tenancy
        : "apackrsct01";
      this.$axios
        .get(
          "/aggregation/securityScores?tenancy_id=" +
            process.env["VUE_APP_TENANCY_" + tenancy]
        )
        .then((result) => {
          this.securityScore = "Security Score " + result.data.securityScore;
          this.riskScore = result.data.riskScore;
          this.securityRating = result.data.securityRating.charAt(0).toUpperCase() + result.data.securityRating.slice(1).toLowerCase();
        });
    },
  },
};
</script>