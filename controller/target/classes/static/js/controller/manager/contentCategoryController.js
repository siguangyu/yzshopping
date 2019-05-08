//控制层
app.controller('contentCategoryController', function ($scope, $controller, contentCategoryService) {

    $controller('baseController', {$scope: $scope});//继承



    // 搜索对象
    $scope.searchMap = {
        'transactionStatus': ''
    };

    //配置分页基本参数
    /*$scope.paginationConf = {
        currentPage: 1, //起始页
        itemsPerPage: 5, // 每页展示的数据条数
        perPageOptions: [5, 10, 20], //可选择显示条数的数组
        onchange:function(){
            selectOrderByCondition($scope.searchMap.transactionStatus);//页面改变（currentPage/itemsPerPage改变）后触发重新查询
        }
    };*/


//根据订单状态进行查询
    $scope.selectOrderByCondition = function (transactionStatus) {
        $scope.searchMap.transactionStatus = transactionStatus;
        contentCategoryService.selectOrderByCondition($scope.searchMap).success(
            function (response) {
                $scope.result = response;
                // console.log(response);
            }
        );
    }



//根据user_good_mapping表的id，去修改交易状态
    $scope.updateByOrderId = function (id) {
        contentCategoryService.updateByOrderId(id).success(
            function (response) {
                // window.location.reload();
                $scope.selectOrderByCondition( $scope.searchMap.transactionStatus);
            }
        );
    }


    /*
    //读取列表数据绑定到表单中  
    $scope.findAll=function(){
        contentCategoryService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        contentCategoryService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne=function(id){
        contentCategoryService.findOne(id).success(
            function(response){
                $scope.entity= response;
            }
        );
    }

    //保存
    $scope.save=function(){
        var serviceObject;//服务层对象
        if($scope.entity.id!=null){//如果有ID
            serviceObject=contentCategoryService.update( $scope.entity ); //修改
        }else{
            serviceObject=contentCategoryService.add( $scope.entity  );//增加
        }
        serviceObject.success(
            function(response){
                if(response.success){
                    //重新查询
                    $scope.reloadList();//重新加载
                }else{
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele=function(){
        //获取选中的复选框
        contentCategoryService.dele( $scope.selectIds ).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                    $scope.selectIds=[];
                }
            }
        );
    }

    $scope.searchEntity={};//定义搜索对象

    //搜索
    $scope.search=function(page,rows){
        contentCategoryService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

*/

});	
