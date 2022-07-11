import App from './App'

// #ifndef VUE3
import Vue from 'vue'

// 导入uView
import uView from '@/uni_modules/uview-ui';
Vue.use(uView)

// 引入顶部导航栏组件
import TopCustom from 'components/TopCustom.vue'
Vue.component("top-custom",TopCustom)

// 引入列表像组件
import ListItem from 'components/ListItem.vue'
Vue.component("list-item",ListItem)

// 引入全局异步请求方法
import * as request from 'utils/request.js'
for( let key in request){
	Vue.prototype[key]=request[key]
}

// 引入store
import store from 'store/index.js'

Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	...App,
	store//注入状态机
})
app.$mount()
// #endif

// #ifdef VUE3
import {
	createSSRApp
} from 'vue'
export function createApp() {
	const app = createSSRApp(App)
	return {
		app
	}
}
// #endif
