app.service("scoreShopService",function($http){
    this.selectAll=function () {
        return $http.post("user/scoreShop/selectAll");
    }
    this. getUserScore=function (id) {
        return $http.get("user/scoreShop/selectScoreByUserId?id="+id);
    }

    this. exchange=function (userId,goodsId) {
        return $http.get("user/scoreShop/score/exchange?userId="+userId+"&goodsId="+goodsId);
    }
    this. scoreDetail=function (userId,page) {
        return $http.get("user/scoreShop/selectScoreDetail?userId="+userId+"&page="+page);
    }
    this. orderDetail=function (userId,page) {
        return $http.get("user/scoreShop/selectOrderDetail?userId="+userId+"&page="+page);
    }
    this. selectScoreShop=function (userId,page) {
        return $http.get("user/scoreShop/selectScoreShop?userId="+userId+"&page="+page);
    }
    this. selectByScore=function (page,score) {
        return $http.get("user/scoreShop/selectByScore?page="+page+"&score="+score);
    }


});
