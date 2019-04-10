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

});
