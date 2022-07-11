const baseURL="http://localhost:8082"

export const $http = function (url,data={},method="GET"){
	return  new Promise((resolve,reject)=>{
		uni.request({
			url:baseURL+url,
			method,
			data,
			success: (res) => {
				resolve(res.data)
			},
			fail(err) { 
				reject(err)
			}
		})
	})
}

export const $get = function (url,data={}){
	return $http(url,data,'GET')
}

export const $post = function (url,data={}){
	return $http(url,data,'POST')
}
