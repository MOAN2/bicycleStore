<template>
	<view class="con wrap">
		<u-back-top :scroll-top="scrollTop"></u-back-top>
		<top-custom :title="pageTitle"></top-custom>

		<template v-if="isAdd">
			<cart-item v-for="(item,index) in cartList" :key="index" :item="item" :index="index"></cart-item>
		</template>

		<u-empty mode="car" icon="http://cdn.uviewui.com/uview/empty/car.png" width="220" height="220" textSize="26"
			v-else>
		</u-empty>

		<view class=" bg-white flex total">
			<view class="flex flex-sub padding align-center">
				<text class="iconfont icon-duigou margin-right " :class="allInfo.allCheck?'bg-yellow':' ' "
					@tap="handleAllCheck(allInfo.allCheck)"></text>
				全选
				<view class="margin-left">
					共计：{{allInfo.allPrice}}
				</view>
			</view>
			<view class="bg-yellow padding text-center color-fff" @tap="openOrder">
				立即结算
			</view>
		</view>


		<!-- 弹窗 -->
		<view>
			<u-overlay :show="showModal" @tap="show = true">
				<view class="warp">
					<view class="rect" @tap.stop>
						<view class="info text-center margin  ">
							<view class="margin-bottom fs-28 color-111">
								{{cartList[cartIndex].title}}
								<view class="margin-tb-sm fs-22 color-92">{{cartList[cartIndex].type}} </view>
								<text class="fs-50 color-111 u-border-bottom">￥{{cartList[cartIndex].price * number}}
								</text>

							</view>
							<view class="flex justify-between u-border-bottom">
								<view class="margin fs-30">
									数量选择
								</view>
								<view class="margin">
									<u-number-box button-size="36" bgColor="#eab352" :min="1" :max="100" integer
										v-model="number"></u-number-box>
								</view>
							</view>

							<view class="flex justify-between margin-top-lg">
								<button type="default" class="cu-btn lg bg-brown" @tap="closeModal">取消</button>
								<button type="default" class="cu-btn lg bg-yellow" @tap="ucNum">确认</button>
							</view>
						</view>
					</view>
				</view>
			</u-overlay>
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
	import cartItem from '../../components/cartItem.vue'
	import order from '../../components/order.vue'
	export default {
		components: {
			cartItem,
			order
		},
		data() {
			return {
				pageTitle: "购物车",
				scrollTop: 0,
				isAdd: false,
				number: 0
			};
		},
		computed: {
			 
			...mapState({
				userinfo: state => state.num.userinfo,
				cartList: state => state.cart.cartList,
				showModal: state => state.cart.showModal,
				cartIndex: state => state.cart.cartIndex,
				allPrice: state => state.cart.allPrice
			}),
			...mapGetters({
				allInfo: 'cart/allInfo'
			})
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onPullDownRefresh() {
			//通过id 获取cart信息
			this.getCart()
			 //判断isAdd
			 if (this.cartList) {
			 	this.isAdd = true
			 
			 }
			setTimeout(function() {
				uni.stopPullDownRefresh();
			}, 1000);
		},
		methods: {
			...mapActions({
				getCart: 'cart/getcartAct'
			}),
			...mapMutations({
				getInfo: 'cart/getInfoMut',
				handleAllCheck: 'cart/allCheckMut',
				setShowModal: 'cart/setShowModalMut',
				updateNum: 'cart/updateNumMut',
				setShoworderModal: 'cart/setShoworderModalMut',
			}),
			//关闭模态框 取消
			closeModal() {
				this.setShowModal(false)
			},
			ucNum() {
				console.log(this.number);
				// 通过index更新number
				this.updateNum(this.number)
				// 关闭模态框
				this.setShowModal(false)
				//更新总价
				this.setAllPrice()
			},
			openOrder() {
				//打开模态框 跳到order页面
				this.setShoworderModal(true)
				uni.navigateTo({
					url: './order'
				})
			}
		},
		onLoad() {
			 
			if (this.userinfo) {
				uni.showToast({
					title: '已登录'
				})
				//查询user info 存储在cart模块中
				try {
					const value = uni.getStorageSync('userinfo');
					if (value) {
						this.getInfo(value)
					}
				} catch (e) {
					// error
				}
				//通过id 获取cart信息
				this.getCart()

				 
				return
			}
			uni.showModal({
				// 登录拦截
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

<style lang="scss">
	.con {
		margin-top: 120rpx;
		height: 150vh;
	}

	.total {
		position: fixed;
		bottom: 100rpx;
		width: 100%;
	}

	.warp {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 100%;
	}

	.rect {
		width: 320px;
		height: 280px;
		background-color: #fff;
	}

	.cu-btn {
		width: 50%;
	}
</style>
