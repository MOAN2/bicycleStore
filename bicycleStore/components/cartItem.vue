<template>
	<view class="container">
		<u-swipe-action>
			<u-swipe-action-item :options="options1" @tap="removeItem">
				<view class="swipe-action u-border-top u-border-bottom">
					<view class="swipe-action__content">
						<view class="flex padding bg-white">
							<view class="flex align-center">
								<text class="iconfont icon-duigou margin-right " @tap="checkItem(index)"
									:class="check?'bg-yellow':' ' "></text>
								<image :src="item.imgUrl" mode="" class="poster margin-right"></image>
							</view>

							<view class="flex justify-between info">
								<view class="margin-bottom fs-28 color-111">
									{{item.title}}
									<view class="margin-tb-sm fs-22 color-92">{{item.type}}</view>
									<text class="fs-50 color-111">￥{{item.price}}</text>

								</view>

								<view class="flex flex-direction align-end">
									<view class="edit margin-bottom-xs">
										<text class="iconfont icon-bianji" @tap="changeNum(index)"></text>
									</view>
									数量 × {{item.number}}
								</view>
							</view>
						</view>
					</view>
				</view>
			</u-swipe-action-item>
		</u-swipe-action>


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
		name: "cartItem",
		props: ['item', 'index'],
		data() {
			return {
				check: false,
				options1: [{
					text: '删除',
					style: {
						backgroundColor: '#f93e41',
					}
				}]
			};
		},
		computed: {
			...mapState({
				// 点击item
				cartIndex: state => state.cart.cartIndex
			})
		},
		methods: {
			checkItem(index) {
				this.check = !this.check
				this.$store.commit('cart/changeCheckMut', {
					index: index,
					val: this.check
				})
				console.log(index);
			},
			changeNum(index) {
				this.setCartIndex(index)
				this.setShowModal(true)
			},
			...mapMutations({
				setCartIndex: 'cart/setCartIndexMut',
				setShowModal: 'cart/setShowModalMut'
			}),
			removeItem(){
				console.log("删除购物车项目");
				this.$get('/cart/removeById',{
					id:this.item.id
				}).then(res =>{
					console.log(res);
					this.$refs.uToast.success(`删除成功，请下拉刷新`)
				})
			}
		},
		onLoad() {

		}
	}
</script>

<style lang="scss">
	page {
		margin-bottom: 100rpx;
	}

	.poster {
		width: 200rpx;
		height: 200rpx;
		background-color: #d8d8d8;
	}

	.info {
		width: 60%;

		.edit {
			width: 130rpx;
			height: 130rpx;
			line-height: 80rpx;
			text-align: center;
		}
	}

	.u-page {
		padding: 0;
	}

	.u-demo-block__title {
		padding: 10px 0 2px 15px;
	}

	.swipe-action {
		&__content {
			padding: 25rpx 0;

			&__text {
				font-size: 15px;
				color: $u-main-color;
				padding-left: 30rpx;
			}
		}
	}
</style>
