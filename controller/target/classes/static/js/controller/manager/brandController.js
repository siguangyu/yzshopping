//品牌控制层
	app.controller('brandController', function($scope,$controller, brandService) {

		$controller('baseController',{$scope:$scope});
		
		//查询品牌列表
		$scope.findAll = function() {
			brandService.findAll().success(function(response) {
				$scope.list = response;
			});
		}

		

		//分页
		$scope.findPage = function(page, size) {
			brandService.findPage(page,size).success(function(response) {
						$scope.list = response.rows;
						$scope.paginationConf.totalItems = response.total;
					});
		}

		//保存
		$scope.save = function() {

			var object=null;//方法名称
			if ($scope.entity.id != null) {//如果有ID
				object=brandService.update($scope.entity);//则执行修改方法 
			}else{
				object=brandService.add($scope.entity);
			}

			if ($scope.entity.name != null && $scope.entity.firstChar != null) {
				
				object.success(function(response) {
							if (response.success) {
								//弹出成功，并重新加载
								alert(response.message);
								$scope.reloadList();
							} else {
								alert(response.message);
							}
						});
			} else {
				alert("不能为空")
			}

		}
		//根据id查询
		$scope.findById = function(id) {
			brandService.findById(id).success(
					function(response) {
						$scope.entity = response;
					});
		}


		//批量删除
		$scope.del = function() {
			//获取选中的复选框			
			brandService.del($scope.selectIds).success(
					function(response) {
						if (response.success) {
							alert(response.message);
							$scope.reloadList();//刷新列表
						} else {
							alert(response.message);
						}
					});

		}
		$scope.searchEntity = {};
		//条件查询
		$scope.search = function(page, size) {
			brandService.search(page,size,$scope.searchEntity).success(function(response) {
				$scope.list = response.rows;
				$scope.paginationConf.totalItems = response.total;
			});
		}

	});