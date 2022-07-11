<template>
	<view>
		<top-custom :title="pageTitle"></top-custom>
		<u-popup :show="showOrder" @close="closeOrder" mode="right" safeAreaInsetTop safeAreaInsetBottom class="pop">
			<view>
				<text class="margin fs-30">查看订单信息</text>
				<view class="u-demo-block__content margin">
					<u-steps :current="1" activeColor="#3c9cff">
						<u-steps-item title="已下单">
						</u-steps-item>
						<u-steps-item title="正在出库">
						</u-steps-item>
						<u-steps-item title="运输中">
						</u-steps-item>
					</u-steps>
				</view>

				<u-collapse v-for="(order,index) in orders" :key="index">
					<u-collapse-item :title=" '订单：'+(index+1) " name="Order guide" class="margin fs-30  u-border-bottom">
						<view class="margin-top  flex flex-direction ">
							<text class="padding-top fs-30">车辆名称：{{order.title}}</text>
							<text class="padding-top fs-30">收货人姓名：{{order.name}}</text>
							<text class="padding-top fs-30">收货人电话：{{order.tel}}</text>
							<text class="padding-top fs-30">送达地址：{{order.address}}</text>
							<text class="padding-top fs-30">预计送达时间：{{order.time}}</text>
							<text class="padding-top fs-30">订单价格：{{order.price}}</text>
						</view>
					</u-collapse-item>

				</u-collapse>


			</view>
		</u-popup>

		<!-- 注销账号 -->
		<view class="">
			<u-action-sheet :actions="listSetting" :title="titleSetting" :show="showSetting" 
			:closeOnClickOverlay="true"
				 @select="selectClick" cancelText="取消"
				@close="closeSetting"
				></u-action-sheet>
		</view>

		<!-- 个人信息查看 -->	 
			<u-modal :show="showInfo" title="个人信息" :closeOnClickOverlay="true"
				@close="closeInfo"
				@confirm="() => showInfo = false"
			 >
				<view class="margin-top  flex flex-direction ">
					<text class="padding-top fs-30">用户名：{{userinfo.username}}</text>
					<text class="padding-top fs-30">用户电话：{{userinfo.tel}}</text>
					<text class="padding-top fs-30">邮箱：{{userinfo.email}}</text>
					<text class="padding-top fs-30">身份：{{userinfo.type=0?"普通用户":"管理员"}}</text>				 
				</view>		
			</u-modal>			
			 
			 <!-- 地址查看 -->
			 	<u-modal :show="showAdd" title="我的地址" :closeOnClickOverlay="true"
			 		@close="closeAdd"
			 		@confirm="() => showAdd = false"
			 	 >
			 		<view class="margin-top flex justify-around " v-for="(item,index) in adds" :key="index">
			 			<text class="padding-top fs-30">地址{{index+1}}:{{item.address}}</text>		 
			 		</view>		
			 	</u-modal>		

	 <!-- 主页面 -->
		<view class="container">
			<view class="avatar">
				<u-avatar :text="userinfo.username.slice(0,1)" fontSize="28" randomBgColor size="80">
				</u-avatar>
				<u-tag text="1" type="warning" icon="level" class="tag">
				</u-tag>

			</view>
			<view class="name">
				<text>{{userinfo.username}}</text>
			</view>

			<view class="content">
				<u-grid :border="true" @click="click">
					<u-grid-item v-for="(Item,Index) in baseList" :key=" Index">
						<u-icon :customStyle="{paddingTop:30+'rpx'}" :name="Item.name" :size="40">
						</u-icon>
						<text class="grid-text">{{Item.title}}</text>
					</u-grid-item>
				</u-grid>
				<u-toast ref="uToast" />
				<view class="   btn  ">
					<u-button type="primary" icon="close" text="退出登录 " :plain="true" shape="circle" color="green"
						@tap="exitLogin">
					</u-button>
				</view>

			</view>

		</view>

	</view>
</template>

<script>
	// 引入mapState
	import {
		mapState,
		mapGetters,
		mapMutations,
		mapActions
	} from 'vuex'
	export default {
		data() {
			return {
				pageTitle: "个人中心",
				titleSetting: '标题',
				listSetting: [{
					name: '注销账号',
					subname: "快速注销账号",
					color: '#ffaa7f',
					fontSize: '20'
				}],
				showOrder: false,
				showInfo:false,
				showSetting: false,
				showAdd:false,
				baseList: [{
						name: 'order',
						title: '订单'
					},
					{
						name: 'car',
						title: '代发货'
					},
					{
						name: 'car-fill',
						title: '待收货'
					},
					{
						name: 'account',
						title: '个人信息'
					},
					{
						name: 'coupon',
						title: '地址'
					},
					{
						name: 'setting',
						title: '设置'
					}
				]
			}
		},
		computed: {
			userinfo() {
				return this.$store.state.num.userinfo
			},
			orders(){
				return this.$store.state.num.orders
			},
			adds(){
				return this.$store.state.num.adds
			}
		},
		methods: {
			...mapMutations({
				getOrders:'num/getOrdersMut',
				getAdds:'num/getAddsMut'
			}),
			 closeOrder() {
				this.showOrder = false
			},
			closeInfo(){
				this.showInfo = false
			},
			closeSetting() {
				this['showSetting'] = false
			},
			closeAdd() {
				this['showAdd'] = false
			},
			click(name) {
				if (name >= 0 && name <= 2) {
					this.showOrder = true
					// 状态机中的订单为空,查询
					if(this.orders.length == 0){
						this.$get('/order/getListByUserId', {
							userId: this.userinfo.id
						}).then(res => {
							console.log(res.data);
							 this.getOrders(res.data)
						})
					} 				 
				} else if (name == 5) {
					this.showSetting = true
				}else if(name == 3){
					this.showInfo = true
				}else if(name = 4){
					// 状态机中的地址为空,查询
					if(this.adds.length == 0){
						this.$get('/address/getListByUserId', {
							userId: this.userinfo.id
						}).then(res => {
							console.log(res.data);
							 this.getAdds(res.data)
						})
					} 			
					this.showAdd = true
				}
			},
		 
			selectClick(index) {
				this.$get("/user/removeById",{
					id:this.userinfo.id
				}).then(res =>{
					uni.showToast({
						title:"注销成功"
					})
					uni.navigateTo({
						url:'../user/login/index'
					})
				})
			},
			...mapActions({
				exitLogin: 'num/exitAct'
			})

		},
		onLoad() {
			if (this.userinfo) {
				console.log('已登录');
				return
			}
			uni.showModal({
				title: "温馨提示",
				content: "你需要先登录才能继续操作",
				cancelText: "稍后再说",
				confirmText: "立即登录",
				success: ({
					cancel
				}) => {
					if (cancel) {
						uni.navigateBack({
							data: 1
						})
						return
					}
					uni.navigateTo({
						url: '../user/login/index'
					})
				}
			})
		}
	}
</script>

<style lang="scss" scoped>
	page {
		background-color: #f1f1f1;
		height: 500rpx;
	}

	.container {
		background-color: white;
		margin-top: 300rpx;
		height: 840rpx;
		border-radius: 5%;

		.avatar {
			width: 1rpx;
			height: 200rpx;
			margin-left: 290rpx;
			transform: translateY(-75rpx);

			.tag {
				width: 100rpx;
				padding: 0;
				position: relative;
				right: -300rpx;
				top: -60rpx;
			}
		}

		.name {
			text-align: center;
			margin-top: -100rpx;
			font-size: 50rpx;
		}

		.grid-text {
			font-size: 14px;
			color: #909399;
			padding: 10rpx 0 20rpx 0rpx;
			/* #ifndef APP-PLUS */
			box-sizing: border-box;
			/* #endif */
		}

		.btn {
			width: 300rpx;
			margin: 100rpx auto;
		}
	}

	.pop {
		width: 300rpx;
	}
</style>
