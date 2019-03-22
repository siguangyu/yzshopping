app.service('searchService',function($http){
	this.search=function(key){
		return $http.get('data-interface/bijia?key='+key);
	}
});