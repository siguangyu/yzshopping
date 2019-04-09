app.service('searchService',function($http){
	this.search=function(key,pageNo){
		return $http.get('data-interface/bijia?key='+key+'&pageNo='+pageNo);
	}
});