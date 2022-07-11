<template>
	<view>
		<!-- #ifndef APP-NVUE || MP-TOUTIAO -->
		<view class="u-demo-block">
 
			<u-swiper 
				:list="imgList" 
				previousMargin="30" 
				nextMargin="30" 
				circular 
				:autoplay="true" 
				radius="5"
				bgColor="#ffffff"
				height="350"
				>
			</u-swiper>
		</view>
		<!-- #endif -->
	</view>
</template>

<script>
	export default {
		name: "scrollImg",
		props:['imgList'],
		data() {
			return {
				 
			};
		}
	}
</script>

<style lang="scss">
	.uni-margin-wrap {
		width: 690rpx;
		width: 100%;
	}

	.swiper {
		height: 300rpx;
	}

	.swiper-item {
		display: block;
		height: 300rpx;
		line-height: 300rpx;
		text-align: center;
	}

	.swiper-list {
		margin-top: 40rpx;
		margin-bottom: 0;
	}

	.uni-common-mt {
		margin-top: 60rpx;
		position: relative;
	}

	.info {
		position: absolute;
		right: 20rpx;
	}

	.uni-padding-wrap {
		width: 550rpx;
		padding: 0 100rpx;
	}
</style>
