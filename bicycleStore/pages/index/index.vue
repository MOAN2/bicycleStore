<template>
	<view class="con">
		<u-back-top :scroll-top="scrollTop"></u-back-top>
		<top-custom :title="pageTitle"></top-custom>
		<view class="banner">
			<index-title :titleList="titleList[0]"></index-title>
			<scroll-img :imgList="imgList"></scroll-img>
		</view>

		<view class="activity">
			<index-title :titleList="titleList[1]"></index-title>
			<u-scroll-list>
				<view v-for="(item, index) in actList" :key="index">
					<image :src="item.thumb"></image>
				</view>
			</u-scroll-list>
		</view>

		<view class="recommend">
			<index-title :titleList="titleList[2]"></index-title>
			<!-- 本季推荐 -->
			<list-item :list="recomList"></list-item>
		</view>

	</view>
</template>

<script>
	import scrollImg from '../../components/scrollImg.vue'
	import indexTitle from '../../components/indexTitle.vue'
	export default {
		components: {
			scrollImg,
			indexTitle
		},
		data() {
			return {
				recomType: "死飞车",
				pageTitle: "网上车行",
				titleList: [{
						icon: "iconfont icon-tuijian",
						title: "限时抢购",
						des: "Time Limit",
						type: "618大促"
					},
					{
						icon: "iconfont icon-liwuhuodong",
						title: "最新活动",
						des: "activity",
						type: "参与有奖"
					}, {
						icon: "iconfont icon-chelun",
						title: "本季推荐",
						des: "Fixed Gear Bicycle",
						type: "死飞车"
					}
				],
				imgList: [
					'/static/imgs/index.jpg',
					'/static/imgs/index2.jpg',
					'/static/imgs/index3.jpg',
					'/static/imgs/index4.jfif',
					'/static/imgs/index5.jfif'

				],
				actList: [{
					thumb: "/static/imgs/act1.png"
				}, {
					thumb: "/static/imgs/act2.png"
				}, {
					thumb: "/static/imgs/act3.png"
				}, {
					thumb: "/static/imgs/act4.png"
				}, {
					thumb: "/static/imgs/act5.png"
				}],
				recomList: [],
				scrollTop: 0
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		methods: {

		},
		onLoad() {
			// 请求接口
			 this.$get('/bicycle/getListByType',{type:"死飞车"}).then(res=>{
				 console.log(res.data)
				 this.recomList = res.data
			 }) 
		}
	}
</script>

<style lang="scss">
	.con {
		margin-top: 120rpx;
		height: 160vh;
	}
</style>
