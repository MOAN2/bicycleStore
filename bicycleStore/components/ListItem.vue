<template>
	<view>
		<view class="container">
			<view class="box" v-for="(item,index)  in list" :key="index" @tap="setDetail(index)">
				<view class="list-info">
					<view class="list-img">
						<img :src="item.imgUrl" alt="商品">
					</view>
					<view class="title  ">
						<u--text :lines="1" :text="item.title"></u--text>
				 
					</view>
		
					<view class="type fs-12">
						<text>{{item.type}}</text>
					</view>
					<view class="price  ">
						<i>¥ </i>
						<text>{{item.price}}</text>
					</view>
		
				</view>
				<view class="list-carticon">
					<text class="iconfont  icon-24gf-cartFull6 "></text>
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
		name:"ListItem",
		props:['list'],
		data() {
			return {			
			};
		},
		methods:{
			...mapMutations({
				getDetail:'num/getDetailMut'
			}),
			setDetail(index){
				console.log('item被点击'+index);
				this.getDetail(index)
				// 将数据存储到本地 成功后跳转到详情页	 
				console.log(this.list[index]);
				try {
					uni.setStorageSync('detail', this.list[index]);
					uni.navigateTo({
						url:'../details/details'
					})
				} catch (e) {
					console.log(e);
				}
				 
			}		 
		},
		 
	}
</script>

<style lang="scss">
.container {
		display: flex;
		justify-content: space-between;
		flex-wrap: wrap;

		.box {
			width: 350rpx;
			height: 400rpx;
			background-color: #ffffff;
			position: relative;
			margin-bottom: 50rpx;

			.list-carticon {
				.icon-24gf-cartFull6 {
					position: absolute;
					bottom: 5%;
					right: 10%;
					color: #FFD700;
					font-size: 60rpx;
					transition: 1s;
				}

				.icon-24gf-cartFull6:hover {
					color: #fd8b00;
				}
			}
		}

		.list-img {
			width: 290rpx;
			height: 230rpx;
			margin-left: 30rpx;

			img {
				width: 100%;
				height: 100%;
			}

		}

		.title,
		.type {
			margin: 7rpx 20rpx;
		}
		.title{
			font-size: 36rpx;
		}
		.price {
			margin: 10rpx 20rpx;
			font-size: 42rpx;
			font-weight: 400;
		}
	}
</style>