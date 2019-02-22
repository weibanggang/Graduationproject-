import Vue from 'vue'
import './plugins/axios'
import main from './main.vue'
import './plugins/iview.js'
import router from './router'
//引入echart：
import echarts from 'echarts'
//注册
Vue.prototype.$echarts = echarts


Vue.config.productionTip = false


new Vue({
  router,
  render: h => h(main)
}).$mount('#app')
