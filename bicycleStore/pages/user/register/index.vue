<template>
	<view class="zai-box">
		<image src="/static/imgs/register.png" mode='aspectFit' class="zai-logo"></image>
		<view class="zai-title">欢迎注册</view>
		<view class="zai-form">
			<input class="zai-input" placeholder-class="true" placeholder="请输入用户名" v-model="info.username" />
			<input class="zai-input" placeholder-class="true" password placeholder="请输入密码" v-model="info.password1" />
			<input class="zai-input" placeholder-class="true" password placeholder="请再输入一次密码"
				v-model="info.password2" />
			<button class="zai-btn" @tap="rejister">立即注册</button>
			<navigator url="../login/index" open-type='navigateBack' hover-class="none" class="zai-label">已有账号，点此去登录.
			</navigator>
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: true,
				hintList: [{
						type: 'error',
						icon: false,
						title: '失败主题',
						message: "",
						iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/error.png'
					},
					{
						type: 'success',
						title: '成功主题',
						message: "注册成功",
						iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/success.png'
					}
				],
				info: {
					username: '',
					password1: '',
					password2: '',
					type: 0
				}
			}
		},
		methods: {
			showToast(params) {
				this.$refs.uToast.show({
					...params,
					complete() {
						params.url && uni.navigateTo({
							url: params.url
						})
					}
				})
			},
			rejister() {
				if (this.info.username) {
					this.$get('/user/getInfoByName', {
						username: this.info.username
					}).then(res => {
						// 有用户数据 注册失败 
						console.log(res.data);
						if (res.data != null) {
							this.hintList[1].message = "已有账号，请登录"
							this.showToast(this.hintList[1])
						} else {
							//	没有账号,判断p1 p2
							if (this.info.password1 == this.info.password2) {
								this.$post('/user/addUser', {
									username: this.info.username,
									password: this.info.password2,
									type: 0
								}).then(res => {
									if (res.code == 200) {
										this.hintList[1].message = "注册成功，请登录"
										this.showToast(this.hintList[1])
									//	this.navigate()
									}
								})
							} else {
								this.hintList[0].message = "两次密码不同"
								this.showToast(this.hintList[0])
							}

						}
					})
				} else {
					// 不能为空
					this.hintList[0].message = "账号不能为空"
					this.showToast(this.hintList[0])
				}
				//console.log(this.info.username);
			},
			navigate() {
				setInterval(() => {
					uni.navigateTo({
						url: '/pages/user/login/index'
					})
				}, 1000)
			}

		}
	}
</script>

<style>
	.zai-box {
		padding: 0 100upx;
		position: relative;
	}

	.zai-logo {
		width: 100%;
		width: 100%;
		height: 310upx;
	}

	.zai-title {
		position: absolute;
		top: 0;
		line-height: 360upx;
		font-size: 68upx;
		color: #fff;
		text-align: center;
		width: 100%;
		margin-left: -100upx;
	}

	.zai-form {
		margin-top: 300upx;
	}

	.zai-input {
		background: #e2f5fc;
		margin-top: 30upx;
		border-radius: 100upx;
		padding: 0upx 40upx;
		font-size: 36upx;
	}

	.input-placeholder,
	.zai-input {
		color: #94afce;
	}

	.zai-label {
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}

	.zai-btn {
		background: #21b108;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
		margin-top: 60upx;
	}

	.zai-btn:after {
		border: 0;
	}

	/*按钮点击效果*/
	.zai-btn.button-hover {
		transform: translate(1upx, 1upx);
	}
</style>
