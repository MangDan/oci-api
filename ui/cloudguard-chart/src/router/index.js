import Vue from 'vue'
import VueRouter from 'vue-router'
import AggregatedProblems from '../views/AggregatedProblems.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/chart/aggregated_problems',
    name: 'AggregatedProblems',
    component: AggregatedProblems
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
