<script>
	export default {	 
		onLaunch: function() {
			console.log('App Launch')
			try {
				const value = uni.getStorageSync('userinfo');
				if (value) {
					this.$store.commit('num/getUserInfoMut',value)
				}
			} catch (e) {
				// error
				console.log("提取用户信息失败");
			}
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	// 导入iconfont
	@import "static/icon/iconfont.css";
	
	// 导入uView
	@import "@/uni_modules/uview-ui/index.scss";
	
	@import '@/colorui/main.css';
	@import '@/colorui/icon.css';
</style>
