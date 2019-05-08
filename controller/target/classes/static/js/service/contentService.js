app.service("contentService",function($http){
	//根据分类ID查询广告列表
	/*this.findByCategoryId=function(categoryId){
		return $http.get("content/findByCategoryId.do?categoryId="+categoryId);
	}*/
		this.signed=function (id) {
			return $http.get("user/signed?id="+id);
        }

    this.issigned=function (id) {
        return $http.get("user/issigned?id="+id);
    }
});
