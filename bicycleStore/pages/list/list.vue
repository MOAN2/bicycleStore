<template>
	<view class="con">
		<u-back-top :scroll-top="scrollTop"></u-back-top>
		<i class="iconfont icon-fenlei " @tap="openPop(true)"></i>
		<top-custom :title="pageTitle" :show="show"></top-custom>
		
		<!-- 列表 -->
		<list-item :list="list" @tap="setDetail"></list-item>

<!-- 左侧分类栏目 -->
		<u-popup :show="show" mode="left" @close="closePop(false)">
			<view class="pop-cont">
				<u-collapse>
					<u-collapse-item title="按类型分类" name="type">
						<u--text type="warning" v-for="(type,index) in typeList" :key="index" :text="type"
							class="padding" @tap="updateList(type)"></u--text>
					</u-collapse-item>


					<u-collapse-item title="按年龄分类" name="sort">
						<u--text type="success" v-for="(sort,index) in sortList" :key="index" :text="sort.title"
							class="padding" @tap="updateList(sort.sortNum)"></u--text>
					</u-collapse-item>

					<view class="allBcBox flex justify-around">
						 
						<u--text text="所有车辆" class="padding u-border-bottom" @tap="getAllBicycle"></u--text>
						<view class="tagBox padding-top-sm padding-right-xs">
							<u-tag :text="allBcNum" plain size="mini" type="warning"></u-tag>
						</view>
					</view>


				</u-collapse>
			</view>
		</u-popup>
	</view>
</template>

<script>
	 // 引入mapState 
	import {mapState,mapGetters,mapMutations,mapActions} from 'vuex'
	export default {
		data() {
			return {
				pageTitle: "分类",
				scrollTop: 0,
				allBcNum: 0,
				list: [],
				typeList: ['死飞车', '山地车', '折叠车', '青年车', '儿童车', '小童车'],
				sortList: [{
						title: "成人车",
						sortNum: 0
					},
					{
						title: "儿童车",
						sortNum: 1
					}
				]
			}
		},
		computed:{
			 // 对象形式
			    ...mapState({
					show:state =>state.num.show,
					detailIndex:state => state.num.detailIndex,
					userinfo: state => state.num.userinfo,
				})
			// show(){
			// 	return this.$store.state.num.show
			// }
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onPullDownRefresh() {
			 
			setTimeout(function() {
				uni.stopPullDownRefresh();
			}, 1000);
		},
		methods: {
			...mapMutations({
				openPop:'num/OPEN',
				closePop:'num/CLOSE'
			}),				 
			// },
			updateList(opt) {
				console.log(isNaN(opt));
				if (isNaN(opt)) {
					// 根据type请求类型
					this.$get('/bicycle/getListByType', {
						type: opt
					}).then(res => {
						//	console.log(res.data)
						this.list = res.data
					})
				} else {
					// 根据sort请求类型
					this.$get('/bicycle/getListBySort', {
						sort: opt
					}).then(res => {

						this.list = res.data
					})
				}
			},
			getAllBicycle() {
				// 默认请求获取所有车辆接口
				this.$get('/bicycle/getAllBicycle').then(res => {
					//	console.log(res.data)
					this.list = res.data
					this.allBcNum = res.data.length
				})
			},
			setDetail(){
				console.log('item被点击');
				uni.setStorage({
					key:'detail',
					data:this.list[this.detailIndex],
					success: function () {
							uni.navigateTo({
								url:'../details/details'
							})
						}
				})
			}
		},
		onLoad() {
			this.getAllBicycle()

		}
	}
</script>

<style lang="scss">
	.con {
		margin-top: 120rpx;
		height: 200vh;
	}

	.icon-fenlei {
		position: absolute;
		z-index: 999;
		right: 105rpx;
		font-size: 70rpx;
		top: -115rpx;
	}

	.pop-cont {
		width: 300rpx;
	}
.allBcBox{
	width: 300rpx;
}
	.tagBox {
		width: 60rpx;

	}
</style>
