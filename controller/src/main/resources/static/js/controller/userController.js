//控制层
app.controller('userController', function ($scope, $controller, userService) {
    //注册
    $scope.reg = function () {
        if ($scope.entity.password != $scope.password) {
            alert("两次输入的密码不一致，请重新输入");
            return;
        }
        userService.add($scope.entity).success(
            function (response) {
                alert(response.message+"!去登陆");
                if (response.code==200){
                    window.location="../login.html";
                }
                //注册成功之后，把
            }
        );
    }

    //登录
    $scope.login = function () {

        userService.login($scope.entity).success(
            function (response) {
                alert(response.message);
                if (response.code == 200) {
                    //登录成功之后, 把个人信息存储到sessionStoreger中
                    var userName = response.data.userName;
                    var userId=response.data.userId;
                    sessionStorage.setItem("userId",userId);
                    sessionStorage.setItem("userName",userName);
                    //跳转到首页
                    window.location="../index.html";
                }
            }
        );
    }

    //发送验证码
    /*$scope.sendCode=function(){
        if($scope.entity.phone==null){
            alert("请输入手机号！");
            return ;
        }
        userService.sendCode($scope.entity.phone).success(
            function(response){
                alert(response.message);
            }
        );
    }
*/


});
