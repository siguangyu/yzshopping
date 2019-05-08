app.controller('indexController',function($scope,$controller,loginService){
	//读取当前登录人
	$scope.showLoginName=function(){
		// loginService.loginName().success(
		// 		function(response){
		// 			$scope.loginName=response.loginName;
		// 		}
		// );
        $scope.managerName = sessionStorage.getItem("managerName");
        $scope.managerId = sessionStorage.getItem("managerId");
        if ($scope.managerName=="undefined"){
            sessionStorage.removeItem("managerName");
        }
        if ($scope.managerId=="undefined"){
            sessionStorage.removeItem("managerId");
        }
        if ($scope.managerId!=null){

		}
		if ($scope.managerName!=null){
        	$scope.loginName=$scope.managerName;
		}
        if ($scope.managerName==null&&$scope.managerId ==null){
            window.location="../manager/login.html"
        }


		
	}

	//注销
	$scope.logout=function(){
        //删除sessionStorage中的managerId和managerName
        sessionStorage.removeItem("managerName");
        sessionStorage.removeItem("managerId");
        //跳转到登录界面
        $scope.managerName = sessionStorage.getItem("managerName");
        $scope.managerId = sessionStorage.getItem("managerId");
        if ($scope.managerName==null&&$scope.managerId ==null){
            window.location="../manager/login.html"
        }
    }
	
});