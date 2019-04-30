app.service('searchService',function($http){
	this.search=function(key,pageNo){
		return $http.get('data-interface/bijia?key='+key+'&pageNo='+pageNo);
	}

    this.search2=function(searchMap){
        return $http.post('data-interface/search',searchMap);
    }


    this.loaddomain=function(searchKey){
        return $http.get('data-interface/loaddomain?searchKey='+searchKey);
    }
});