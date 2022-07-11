import Vue from "vue"
import Vuex from "vuex"
import num from "./num.js"
import cart from "./cart.js"

Vue.use(Vuex)
const store = new Vuex.Store({
	 modules:{		 
		 num,
		 cart
	 }
})

export default store