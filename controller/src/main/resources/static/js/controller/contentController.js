//广告控制层（运营商后台）
app.controller("contentController", function ($scope, contentService) {
    /*	$scope.contentList=[];//广告集合
        $scope.findByCategoryId=function(categoryId){
            contentService.findByCategoryId(categoryId).success(
                function(response){
                    $scope.contentList[categoryId]=response;
                }
            );
        }*/
    //搜索跳转
    $scope.search = function () {
        location.href = "../search.html#?key=" + $scope.key;
    }

    $scope. searchByHotKey = function (key) {
        location.href = "../search.html#?key=" + key;
    }

    //加载首页时，获取用户名和userId,如果为空，则显示“请登录注册”，不为空则显示用户名和退出登录
    $scope.getUserInfo = function () {
        $scope.userName = sessionStorage.getItem("userName");
        $scope.userId = sessionStorage.getItem("userId");
        if ($scope.userName != null) {
            document.getElementById("userLoginShowFrame").setAttribute("style","display:none");
            document.getElementById("userName").setAttribute("style","display:block");
        }
        if ($scope.userName == null) {
            document.getElementById("userLoginShowFrame").setAttribute("style","display:block");
            document.getElementById("userName").setAttribute("style","display:none");
        }

    }
    //搜索跳转
    $scope.logout = function () {
        sessionStorage.clear();
    }


});