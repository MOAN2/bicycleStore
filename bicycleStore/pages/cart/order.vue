<template>
	<view>
		<!-- 弹窗 -->
		<view>
			<u-overlay :show="showorderModal" @tap="show = true">
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

											<u-form-item label="电话" borderBottom ref="item1">
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
											@tap="closeOrder ">
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
	import {
		mapState,
		mapGetters,
		mapMutations,
		mapActions
	} from 'vuex'

	export default {
		name: "order",
		data() {
			return {
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
				showorderModal: state => state.cart.showorderModal
			})

		},
		methods: {
			...mapMutations({
				getInfo: 'cart/getInfoMut',
				handleAllCheck: 'cart/allCheckMut',
				setShoworderModal: 'cart/setShoworderModalMut',
				updateNum: 'cart/updateNumMut'
			}),

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
				console.log(this.$refs.form1.validate());
				this.$refs.form1.validate().then(res => {
					uni.$u.toast('信息校验通过，正在为您下单...')
					this.cartList.forEach(item =>{
						if(item.isCheck){
							console.log("购物车列表为勾选状态："+item.isCheck);
							this.$post('/order/addOrder', {
								userId: this.userinfo.id,
								bicycleId: item.bicycleId,
								state: 0,
								price: item.price*item.number,
								title:item.title,
								name: this.model1.order.name,
								address: this.model1.address,
								tel: this.model1.order.tel,
								time: this.model1.order.time,
								isDelivery: this.model1.order.isDelivery = "自取" ? 0 : 1,
								remark: this.model1.remark
							}).then(res => {
								console.log(res);
							})
						}
					})
					// 提交后跳回cart
					 uni.switchTab({
					 	url:'./cart'
					 })
				}).catch(errors => {
					uni.$u.toast('信息校验失败')
				})
			},
			reset() {
				const validateList = ['order.name', 'order.sex', 'radiovalue', 'remark',
					'order.time', 'order.tel'
				]
				this.$refs.form1.resetFields()
				this.$refs.form1.clearValidate()
				setTimeout(() => {
					this.$refs.form1.clearValidate(validateList)
					// 或者使用 this.$refs.form1.clearValidate()
				}, 10)
			},
			closeOrder(){
				this.setShoworderModal(false)
				// 取消后跳回cart
				 uni.switchTab({
				 	url:'./cart'
				 })
			},
			hideKeyboard() {
				uni.hideKeyboard()
			},

		}
	}
</script>

<style lang="scss">
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
