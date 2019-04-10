app.controller("scoreShopController", function ($scope, scoreShopService,$window) {

//搜索全部积分商品
    $scope.selectAll = function () {
        scoreShopService.selectAll().success(
            function (response) {
                console.log(response);
                $scope.resultMap = response;

            });

    }

    $scope.getItemDetail = function () {
        $scope.item = JSON.parse(sessionStorage.getItem("item"));
    }

    $scope.putItem = function (item) {
        sessionStorage.setItem("item", JSON.stringify(item));
    }

    $scope.scoreExchange = function () {
        //获取商品积分
        var item = JSON.parse(sessionStorage.getItem("item"));
        var gPrice = item.gPrice;

        //获取用户id
        var userId = sessionStorage.getItem("userId");
        if (userId == null) {
            //跳转到首页
            window.location = "../login.html";
        }
        //根据id，获取用户积分
        else {
           var userScore= $scope.getUserScore(userId);
           if (userScore<gPrice){
               alert("积分不足");
           }
            //积分足够，进行兑换
          var res=$scope.exchange(userId,item.id);
           alert(res);
        }

    }

//根据id，获取用户积分
    $scope.getUserScore = function (userId) {
        scoreShopService.getUserScore(userId).success(
            function (response) {
                return response.data;
            });
    }
    //积分足够，进行兑换
    $scope.exchange = function (userId,goodsId) {
        scoreShopService.exchange(userId,goodsId).success(
            function (response) {
                if (response.code==200){
                  var item =JSON.parse(sessionStorage.getItem("item"));
                    console.log(item);
                  var gNumber=item.gNumber;
                  item.gNumber=gNumber-1;
                  alert(item.gNumber);
                  item= JSON.stringify(item);
                   sessionStorage.setItem("item",item);
                    $window.location.reload();
                    return "兑换成功!"
                }
            });
    }
    /*   var i=1;
   $scope.testReload=function () {
       //测试刷新

       $window.location.reload();
       console.log(i++);
   }*/
});