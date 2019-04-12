app.controller('userController', function ($scope, userService) {

//修改个人信息
    $scope.updateUserInfo=function(){

        // $scope.entity.year =document.getElementById("select_year2").value;
        // $scope.entity.month =document.getElementById("select_month2");
        // $scope.entity.day =document.getElementById("select_day2");

        // alert($scope.entity);
        $scope.entity.id=sessionStorage.getItem("userId");
        userService.updateUserInfo($scope.entity).success(
            function(response){
                alert(response.message);

                if (response.code==200){
                    //设置 sessionStorage中用户名的值
                    console.log(response.data.username);
                    sessionStorage.setItem("userName",response.data.username);
                    location.reload();
                }
            }

        );
    }


    //selectByPrimaryKey根据用户id查询用户信息
    $scope.selectByPrimaryKey=function () {
        var userId=sessionStorage.getItem("userId");
        if(userId!=null){
            userService.selectByPrimaryKey(userId).success(
              function(response){
                  $scope.userEntity=response;
                  $scope.year=$scope.userEntity.data.year;
                  // $scope.entity.year = $scope.year;
                  $scope.month=$scope.userEntity.data.month;
                  $scope.day=$scope.userEntity.data.day;

                  var yearSel = document.getElementById("select_year2").options;

                  yearSel[0].innerHTML = $scope.year
                  yearSel[0].value = $scope.year

                  var monthSel = document.getElementById("select_month2").options;
                  monthSel[0].innerHTML = $scope.month
                  monthSel[0].value = $scope.month

                  var daySel = document.getElementById("select_day2").options;
                  daySel[0].innerHTML = $scope.day
                  daySel[0].value = $scope.day
              }
            );
        }else {
            alert("未登录");
            window.location="../login.html";
        }
    }


    //注册
    $scope.reg = function () {
        if ($scope.entity.password != $scope.password) {
            alert("两次输入的密码不一致，请重新输入");
            return;
        }
        userService.add($scope.entity).success(
            function (response) {

                if (response.code==200){
                    alert("注册成功！积分+10！",response.message+"!去登陆");
                    window.location="../login.html";
                }
                else{
                    alert("请正确输入！");
                }
            }
        );
    }

    //登录
    $scope.login = function () {

        userService.login($scope.entity).success(
            function (response) {
                // alert(response.message);
                // console.log(response.message);
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

    $scope.logout = function () {
        sessionStorage.clear();
    }




});
