export default {
	namespaced:true,
	state(){
		return {
			show:true,
			userinfo:null,
			detailIndex:0,
			orders:[],
			adds:[]
		}
	},
	mutations:{
		OPEN(state,value){
			state.show = value
		},
		CLOSE(state,value){
			 state.show = value
		},
		getUserInfoMut(state,value){
			state.userinfo = value
			uni.setStorage({
				key:'userinfo',
				data:value
			})
		},
		getDetailMut(state,value){
			state.detailIndex = value
		},
		getOrdersMut(state,value){
			state.orders = value
		},
		getAddsMut(state,value){
			state.adds=value
		}
		
	},
	actions:{
		exitAct(context,value){
			// 清理orders
			context.commit('getOrdersMut',[])
			// 清理orders
			context.commit('getAddsMut',[])
			// 清楚本地存储的userinfo
			uni.removeStorage({
				key: 'userinfo',
				success: function (res) {
					uni.navigateTo({
						url: '/pages/user/login/index'
					})
				}
			});
		}
	}
}