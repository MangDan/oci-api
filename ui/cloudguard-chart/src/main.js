import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import vuetify from './plugins/vuetify'

axios.defaults.baseURL = 'https://iqbptw63tfjg5gzkidq6lputyu.apigateway.us-ashburn-1.oci.customer-oci.com/cloudguard'
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.get["Content-Type"] = "application/json";

Vue.config.productionTip = false

Vue.prototype.$axios = axios;

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
