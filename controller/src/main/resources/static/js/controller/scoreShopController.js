app.controller("scoreShopController", function ($scope, scoreShopService,$window) {

//搜索全部积分商品
    $scope.selectAll = function () {
        scoreShopService.selectAll().success(
            function (response) {
                // console.log(response);
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
          // $scope.exchange(userId,item.id);

            scoreShopService.exchange(userId,item.id).success(
                function (response) {
                    if (response.code==200){
                        var item =JSON.parse(sessionStorage.getItem("item"));
                        console.log(item);
                        var gNumber=item.gNumber;
                        item.gNumber=gNumber-1;
                        // alert(item.gNumber);
                        item= JSON.stringify(item);
                        sessionStorage.setItem("item",item);
                        alert("兑换成功");

                        //刷新当前页面，为了刷新数量
                        $window.location.reload();
                        //刷新父页面，为了刷新数量
                        window.opener.location.reload()
                    }else{
                        alert(response.message);
                    }
                });
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
                  // alert(item.gNumber);
                  item= JSON.stringify(item);
                   sessionStorage.setItem("item",item);
                    $window.location.reload();

                }
            });
    }

/*积分详情*/
    $scope.scoreDetail = function (page){
        var userId=sessionStorage.getItem("userId");
        if (userId==null){
            alert("请登录！");
            window.location = "../login.html";
        }
        scoreShopService.scoreDetail(userId,page).success(
            function (response) {
                $scope.scoreList=response;
            }
        )

    }

    $scope.selectScoreShop=function(page){
        var userId=sessionStorage.getItem("userId");
        if (userId==null){
            alert("请登录！");
            window.location = "../login.html";
        }
            scoreShopService.selectScoreShop(userId,page).success(
                function (response) {
                    $scope.resultMap=response;
                });
        }
    $scope.selectByScore=function(page,num){
       var ul= document.getElementById("one").getElementsByTagName("li");
       var score=ul[num].getAttribute("value");
        scoreShopService.selectByScore(page,score).success(
            function (response) {
                $scope.resultMap=response;
            });
    }
    /*$scope.selectByScoreTwo=function(page){
        var score= document.getElementById("two").text;
        scoreShopService.selectByScore(page,score).success(
            function (response) {
                $scope.resultMap=response;
            });
    }*/


});