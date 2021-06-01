import Vue from 'vue'
import VueRouter from 'vue-router'
import About from '../views/About.vue'
import AggregatedProblemsView from '../views/AggregatedProblemsView.vue'
import SecurityScoresView from '../views/SecurityScoresView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/cloudguard-chart/aggregated_problems',
    name: 'aggregatedProblemsView',
    component: AggregatedProblemsView
  },
  {
    path: '/cloudguard-chart/security_scores',
    name: 'securityScoresView',
    component: SecurityScoresView
  },
  {
    path: '/',
    name: 'about',
    component: About
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
