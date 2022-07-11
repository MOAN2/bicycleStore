<template>
	<view class="zai-box">
		<image src="/static/imgs/login.png" mode='aspectFit' class="zai-logo"></image>
		<view class="zai-title">欢迎登录</view>
		<view class="zai-form">
			<input class="zai-input" placeholder-class="true" placeholder="请输入用户名" v-model="info.username" />
			<input class="zai-input" placeholder-class="true" password placeholder="请输入密码" v-model="info.password" />
			<view class="zai-label">忘记密码？</view>
			<button class="zai-btn" @tap="login">立即登录</button>
			<navigator url="../register/index" hover-class="none" class="zai-label">还没有账号？点此注册.</navigator>
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
						message: "登录失败",
						iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/error.png'
					},
					{
						type: 'success',
						title: '成功主题',
						message: "登录成功",
						iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/success.png'
					}
				],
				info: {
					username: '',
					password: '',
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
			login() {
				if (this.info.username && this.info.password) {
					this.$get('/user/getInfoByName', {
						username: this.info.username
					}).then(res => {
						if (res.data[0].password == this.info.password) {
							// 密码正确
							this.showToast(this.hintList[1])		
							console.log(res.data[0]);
							this.$store.commit('num/getUserInfoMut',res.data[0])				 
							this.navigate()
						} else {
							// 密码错误
							this.hintList[0].message = "密码错误"						
							this.showToast(this.hintList[0])
							this.info.username=''
							this.info.password=''
						}
					})
				}else{
					// 不能为空
					this.hintList[0].message = "账号或密码不能为空"
					this.showToast(this.hintList[0])				
				}
				//console.log(this.info.username);
			},
			navigate() {			 
				setInterval(() => {
					uni.switchTab({
						url:'../../index/index'
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
	}

	.zai-btn:after {
		border: 0;
	}

	/*按钮点击效果*/
	.zai-btn.button-hover {
		transform: translate(1upx, 1upx);
	}
</style>
