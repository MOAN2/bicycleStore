<template>
	<view>
		<top-custom :title="pageTitle"></top-custom>
		 
		<template v-if="isGet">
			<u-notice-bar :text="text1"></u-notice-bar>
			<view class=" ">
				<image :src="itemDetail.imgUrl" mode="aspectFit" style="width: 750rpx; height: 300px;"></image>
			</view>
			<view class="">
				<view class="u-page__item">
					<text class="u-page__item__title">车辆详情介绍</text>
					<u-cell-group>
						<u-cell size="large" title="车辆名称" :value="itemDetail.title" label="车辆名称由生产厂家规定"></u-cell>
						<u-line color="#ffa322"></u-line>
						<u-cell size="large" title="车辆类型" :value="itemDetail.type" label="车辆相关类型"></u-cell>
						<u-line color="#ffa322"></u-line>
						<view class="flex justify-between">
							<u-cell size="large" title="单价" label="单位/元"></u-cell>
							<u-tag :text="itemDetail.price" type="warning" class="padding-right margin-top fs-28"
								size="large"></u-tag>
						</view>
						<u-line color="#ffa322"></u-line>
						<u-cell size="large" title="使用人群" :value="itemDetail.sort==0?'成人车':'儿童车'" label="车辆适合人群">
						</u-cell>
						<u-line color="#ffa322"></u-line>
						<u-cell size="large" title="库存" :value="itemDetail.stock" label="单位/个"></u-cell>
						<u-line color="#ffa322"></u-line>
					</u-cell-group>
				</view>

				<u-collapse accordion>

					<u-collapse-item title="购买须知">
						<text class="u-collapse-content">购买：下单后3天内发货</text>
						<text class="u-collapse-content">配送：可配送至市中心任何地点</text>
					</u-collapse-item>

					<u-collapse-item title="店家信息">
						<view class="flex justify-center">
							<u-icon name="map-fill" color="#ff8b2c" size="28"></u-icon>
							<text class="u-collapse-content padding-left">店家地址：四川省乐山市市中区滨河路</text>
						</view>
						<view class="flex justify-center">
							<u-icon name="phone-fill" color="#ff8b2c" size="28"></u-icon>
							<text class="u-collapse-content padding-left">联系电话：123-456-789-11</text>
						</view>
					</u-collapse-item>

				</u-collapse>
			</view>
			<view class="flex justify-between margin-top-lg">
				<button type="default" class="cu-btn lg bg-brown" @tap="buy(true)">立即购买</button>
				<button type="default" class="cu-btn lg bg-yellow color-fff" @tap="addCart">加入购物车</button>
			</view>
		</template>
		<u-loading-icon  color="green" class="margin-top-lg" text="正在加载" v-else></u-loading-icon>

		<!-- 弹窗 -->
		<view>
			<u-overlay :show="showModal" @tap="show = true">
				<view class="warp">
					<view class="rect" @tap.stop>
						<view class="info text-center margin  ">
							<view class="margin-bottom fs-28 color-111">
								<view class="margin-tb-sm fs-30 color-92">立即购买 </view>
							</view>

							<view class="u-page">
								<view class="u-demo-block">
									<text class="u-demo-block__title">请填写订单信息 </text>
									<view class="u-demo-block__content">
										
										<u--form labelPosition="left" :model="model1" ref="form1">
											<u-form-item label="姓名" prop="order.name" borderBottom ref="item1">
												<u--input v-model="model1.order.name" border="none"
													placeholder="姓名,只能为中文"></u--input>
											</u-form-item>

											<u-form-item label="性别" prop="order.sex" borderBottom
												@click="showSex = true; hideKeyboard()" ref="item1">
												<u--input v-model="model1.order.sex" disabled disabledColor="#ffffff"
													placeholder="请选择性别" border="none"></u--input>
												<u-icon slot="right" name="arrow-right"></u-icon>
											</u-form-item>

											<u-form-item label="电话"  borderBottom ref="item1">							 
												<u--input v-model="model1.order.tel" border="none"
													placeholder="填写收货人联系电话" type="number"></u--input>
											</u-form-item>

											<u-form-item label="是否配送" prop="radiovalue" borderBottom ref="item2">
												<u-radio-group v-model="model1.radiovalue">
													<u-radio :customStyle="{marginRight: '16px'}"
														v-for="(item, index) in radiolist" :key="index"
														:label="item.name" :name="item.name">
													</u-radio>
												</u-radio-group>
											</u-form-item>

											<u-form-item label="地址" prop="address" borderBottom ref="item3">
												<u--textarea placeholder="不低于5个字" v-model="model1.address" count
													autoHeight>
												</u--textarea>
											</u-form-item>

											<u-form-item label="备注" prop="remark" borderBottom ref="item3">
												<u--textarea placeholder="不低于3个字" v-model="model1.remark" count
													autoHeight>
												</u--textarea>
											</u-form-item>

											<u-form-item label="送达时间" prop="order.time" borderBottom
												@click="showTime = true; hideKeyboard()" ref="item1">
												<u--input v-model="model1.order.time" disabled disabledColor="#ffffff"
													placeholder="请选择时间" border="none">
												</u--input>
												<u-icon slot="right" name="arrow-right"></u-icon>
											</u-form-item>
										</u--form>

										<u-button type="primary" text="提交" customStyle="margin-top: 20px"
											@click="submit"></u-button>
										<u-button type="error" text="重置" customStyle="margin-top: 10px" @click="reset">
										</u-button>
										<u-button type="warning" text="取消" customStyle="margin-top: 10px"
											@tap="closeModal">
										</u-button>

										<u-action-sheet :show="showSex" :actions="actions" title="请选择性别"
											description="如果选择保密会报错" @close="showSex = false" @select="sexSelect">
										</u-action-sheet>

										<u-datetime-picker :show="showTime" :value="time" mode="date"
											closeOnClickOverlay @confirm="timeConfirm" @cancel="timeClose"
											@close="timeClose"></u-datetime-picker>
									</view>
								</view>
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
	export default {
		data() {
			return {
				text1: "正品保障，放心购买，价格实惠，7天内车辆出现质量问题包退换，购买即包2年维修",
				pageTitle: "商品详情",
				itemDetail: {},
				isGet: false,
				showModal: false,

				showTime: false,
				model1: {
					order: {
						name: '',
						sex: '',
						time: '',
						tel: ''
					},
					radiovalue: '自取',
					remark: '',
					address: ''
				},
				showSex: false,
				time: Number(new Date()),
				actions: [{
						name: '男',
					},
					{
						name: '女',
					},
					{
						name: '保密',
					},
				],
				rules: {
					'order.name': [{
						type: 'string',
						required: true,
						message: '请填写姓名',
						trigger: ['blur', 'change']
					}, {
						// 此为同步验证，可以直接返回true或者false，如果是异步验证，稍微不同，见下方说明
						validator: (rule, value, callback) => {
							// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
							return uni.$u.test.chinese(value);
						},
						message: "姓名必须为中文",
						// 触发器可以同时用blur和change，二者之间用英文逗号隔开
						trigger: ["change", "blur"],
					}],
					'order.tel': [{
						type: 'number',
						len: '11',
						required: true,
						message: '请填写电话',
						trigger: ['blur', 'change'],
					}],
					'order.sex': {
						type: 'string',
						max: 1,
						required: true,
						message: '请选择男或女',
						trigger: ['blur', 'change']
					},
					radiovalue: {
						type: 'string',
						min: 1,
						max: 2,
						trigger: ['change']
					},
					address: {
						type: 'string',
						min: 1,
						required: true,
						message: '不低于5个字',
						trigger: ['change']
					},
					remark: {
						type: 'string',
						min: 1,
						required: true,
						message: '不低于1个字',
						trigger: ['change']
					},
					'order.time': {
						type: 'string',
						required: true,
						message: '请选择时间',
						trigger: ['change']
					}
				},
				radiolist: [{
						name: '自取',
						disabled: false
					},
					{
						name: '配送',
						disabled: false
					}
				],
			}
		},
		onReady() {
			// 如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则
			this.$refs.form1.setRules(this.rules)
		},
		computed: {
			...mapState({
				userinfo: state => state.num.userinfo,
				cartList: state => state.cart.cartList,
			})

		},
		methods: {
			//验证表单
			groupChange(n) {
				// console.log('groupChange', n);
			},
			radioChange(n) {
				// console.log('radioChange', n);
			},
			navigateBack() {
				uni.navigateBack()
			},
			sexSelect(e) {
				this.model1.order.sex = e.name
				this.$refs.form1.validateField('order.sex')
			},
			change(e) {
				// console.log(e);
			},
			timeClose() {
				this.showTime = false
				this.$refs.form1.validateField('order.time')
			},
			timeConfirm(e) {
				this.showTime = false
				this.model1.order.time = uni.$u.timeFormat(e.value, 'yyyy-mm-dd')
				this.$refs.form1.validateField('order.time')
			},
			submit() {
				// 如果有错误，会在catch中返回报错信息数组，校验通过则在then中返回true
				this.$refs.form1.validate().then(res => {
					uni.$u.toast('信息校验通过，正在为您下单...')				 
					this.$post('/order/addOrder',{
						 userId:this.userinfo.id,
						 bicycleId:this.itemDetail.id,
						 state:0,
						 price:this.itemDetail.price,
						 title:this.itemDetail.title,
						 name:this.model1.order.name,
						 address:this.model1.address,
						 tel:this.model1.order.tel,
						 time:this.model1.order.time,
						 isDelivery:this.model1.order.isDelivery="自取"?0:1,
						 remark:this.model1.remark
					 }).then(res =>{
						console.log(res);
					})
					// 添加地址
					this.$post('/address/addAddress', {
						userId: this.userinfo.id,
						address: this.model1.address,								 
					}).then(res => {
						console.log(res);
					})
				}).catch(errors => {
					uni.$u.toast('信息校验失败')
				})
			},
			reset() {
				const validateList = ['order.name', 'order.sex', 'radiovalue', 'remark',
					'order.time','order.tel'
				]
				this.$refs.form1.resetFields()
				this.$refs.form1.clearValidate()
				setTimeout(() => {
					this.$refs.form1.clearValidate(validateList)
					// 或者使用 this.$refs.form1.clearValidate()
				}, 10)
			},
			hideKeyboard() {
				uni.hideKeyboard()
			},

			//其他方法
			//关闭模态框 取消
			closeModal() {
				this.showModal = false
			},
			...mapMutations({
				getInfo: 'cart/getInfoMut',
				setCartIndex: 'cart/setCartIndexMut',
				updateNum: 'cart/updateNumMut'
			}),
			...mapActions({
				getCart: 'cart/getcartAct'
			}),
			loginHoldup(){
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
			},
			buy(value) {
				 
				// 进行登录拦截
				if (this.userinfo) {
					//查询user info 存储在cart模块中
					try {
						const value = uni.getStorageSync('userinfo');
						if (value) {
							this.getInfo(value)
						}
					} catch (e) {
						// error
					}					
					this.showModal = value
				}else{
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
				 
				 
			},
			addCart() {
				// 进行登录拦截
				if (this.userinfo) {
					//查询user info 存储在cart模块中
					try {
						const value = uni.getStorageSync('userinfo');
						if (value) {
							this.getInfo(value)
						}
					} catch (e) {
						// error
					}					
					//通过id 获取cart信息  刷新数据
					this.getCart()
					console.log(this.cartList);
					// 判断是否重复
					console.log(this.cartList);
					if(this.cartList==null){
						this.$post('/cart/addItem', {
							userId: this.userinfo.id,
							bicycleId:this.itemDetail.id,
							title: this.itemDetail.title,
							imgUrl: this.itemDetail.imgUrl,
							number: 1,
							price: this.itemDetail.price,
							type: this.itemDetail.type
						}).then(res => {
							uni.showToast({
								title: '加入购物车成功'
							})
						})
					}else{
						for (let i = 0; i < this.cartList.length; i++) {
							if (this.cartList[i].title == this.itemDetail.title) {
								uni.showToast({
									title: ' 商品已经存在购物车'
								})
								return
							}
						}
												 
					}
					 
				}else{
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
				 
			},
			// 添加到订单
			addOrder() {

			}
		},
		onLoad() {
			try {
				const value = uni.getStorageSync('detail');
				if (value) {
					this.itemDetail = value
					this.isGet = true
				}
			} catch (e) {
				// error
			}
		}
	}
</script>

<style lang="scss">
	.img {
		width: 700rpx;
	}

	.cu-btn {
		width: 50%;
	}

	.warp {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 100%;
	}

	.rect {
		width: 320px;
		height: 650px;
		background-color: #fff;
	}
	 
</style>
