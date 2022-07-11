import {$get} from '@/utils/request.js'
import Vue from 'vue'
export default {
	namespaced:true,
	state(){
		return {
			userinfo:null	,
			cartList:null,
			cartIndex:0,
			showModal:false,
			showorderModal:true,
		}
	},
	getters:{
		allInfo(state){	
			let allCheck = true
			let allPrice = 0
			console.log(333);
			if(state.cartList){
				state.cartList.forEach(item => {
					if(!item.isCheck){
						allCheck = false
					}
					if(item.isCheck){
						allPrice += item.number * item.price
					}
				})
				return {allCheck,allPrice}
			}		 			 
		}
	},
	mutations:{
		getInfoMut(state,value){
		//	console.log(value);
			state.userinfo = value
		},
		getcartMut(state,value){
			state.cartList = value
		//	console.log(state.cartList);
		},
		changeCheckMut(state,value){  //单选
			Vue.set(state.cartList[value.index],'isCheck',value.val)
		//	state.cartList[value.index].isCheck=value.val
		},
		allCheckMut(state,bool){
			console.log('allCheckMut');
			state.cartList.forEach(item =>{
				item.isCheck = !bool
			})
		},
		setCartIndexMut(state,value){
			state.cartIndex = value
		},
		//编辑数量的模态框
		setShowModalMut(state,value){
			state.showModal = value
		},
		//购物车结算模态框
		setShoworderModalMut(state,value){
			state.showorderModal = value
		},
		updateNumMut(state,value){			
			state.cartList[state.cartIndex].number=value
		} 
	},
	actions:{
		getcartAct(context,value){
		//	console.log(context,value);
			 $get('/cart/getListByUserId',{
				 userId:context.state.userinfo.id
			 }).then(res => {
				 context.commit("getcartMut",res.data)	  
			 })
		} 
	}
}