<template>
	<view>
		<u-navbar leftText="返回" :title="title" :safeAreaInsetTop="false">
			<view class="u-nav-slot" slot="left">
				<u-icon name="arrow-left" size="24" @tap="back"></u-icon>
				<u-line direction="row" :hairline="false" length="16" margin="0 8px"></u-line>
				<i class="iconfont icon-zihangche "></i>
			 
			</view>

		</u-navbar>
	</view>
</template>

<script>
	export default {
		name: "TopCustom",
		props: ['title'],
		data() {
			return {
			 
			};
		},
		methods: {
			back() {
				console.log(getCurrentPages());
				uni.navigateBack({
					delta:1
				})		 
			} 
		},
		onLoad() {
			 
		}
	}
</script>

<style lang="scss">
	.icon-zihangche {
		position: absolute;
		right: -630rpx;
		font-size: 70rpx;
		top: 5rpx;
	}

	 
</style>
