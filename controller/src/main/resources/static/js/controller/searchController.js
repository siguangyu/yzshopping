app.controller('searchController', function ($scope, $location, searchService) {




    $scope.putItem = function (item) {
        sessionStorage.setItem("item", JSON.stringify(item));
    }

    // 搜索对象
    $scope.searchMap = {
        'searchKey': '',
        'category': '',
        'brand': '',
        'spec': {},
        'domainch': '',
        'price': '',
        'pageNo': 1,
        'pageSize': 50,
        'sortField': '',
        'sort': ''
    };

    $scope.search2 = function() {
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        $scope.searchMap.searchKey = document.getElementById("autocomplete").value;
        searchService.search2($scope.searchMap).success(function(response) {
            $scope.resultMap = response;// 搜索返回的结果
            if (response.data!=null){
                document.getElementById("error").style.display="none";
            }else{
                //没有搜索结果，显示错误页
                document.getElementById("error").style.display="";
            }
            // console.log( $scope.resultMap);

            buildPageLabel();// 调用分页方法，产生页码数据
            var searchKey=$scope.searchMap.searchKey;
            loaddomain(searchKey);
        });

    }



    $scope.search = function () {
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        $scope.searchMap.searchKey= $scope.key;
        searchService.search($scope.key,$scope.searchMap.pageNo).success(
            function (response) {
                $scope.resultMap = response;// 搜索返回的结果
                // console.log( $scope.resultMap.length);
                if (response.data!=null){
                    document.getElementById("error").style.display="none";
                }else{
                    //没有搜索结果，显示错误页
                    document.getElementById("error").style.display="";
                }
                buildPageLabel();// 调用分页方法，产生页码数据

                var searchKey=$scope.searchMap.searchKey;
                loaddomain(searchKey);
        });

    }

    $scope. searchByHotKey = function (key) {
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        searchService.search(key,$scope.searchMap.pageNo).success(
            function (response) {
                $scope.resultMap = response;// 搜索返回的结果
                buildPageLabel();// 调用分页方法，产生页码数据
            });

    }

    //加载商城名称
    loaddomain = function (searchKey) {
        searchService.loaddomain(searchKey).success(
            function (domainCh) {
                $scope.domain = domainCh;// 搜索返回的结果
            });
    }

    // 添加搜索项
    $scope.addSearchItem = function (key, value) {
        if (key == 'category' || key == 'brand' || key == 'price') {// 如果点击的是分类或者是品牌
            $scope.searchMap[key] = value;
        } else {
            $scope.searchMap.spec[key] = value;
        }
        $scope.search();// 执行搜索
    }

    $scope.addDomainItem = function (value) {
       $scope.searchMap.domainch=value;
        $scope.search2();// 执行搜索
    }

    // 撤销搜索项
    $scope.removeSearchItem = function (key) {
        if (key == 'category' || key == 'brand' || key == 'price') {// 如果点击的是分类或者是品牌
            $scope.searchMap[key] = "";
        } else {
            delete $scope.searchMap.spec[key]; // 移除规格属性
        }
        $scope.search();// 执行搜索
    }

    // 构建分页标签(totalPages为总页数)
    buildPageLabel = function () {
        $scope.pageLabel = [];// 新增分页栏属性
        var maxPageNo = $scope.resultMap.data.pages;// 得到最后页码
        var firstPage = 1;// 开始页码
        var lastPage = maxPageNo;// 截止页码

        $scope.firstDot = true;// 前面有点
        $scope.lastDot = true;// 后边有点

     //   if ($scope.resultMap.totalPages > 5) { // 如果总页数大于5页,显示部分页码
        if (maxPageNo > 5) { // 如果总页数大于5页,显示部分页码
            if ($scope.searchMap.pageNo <= 3) {// 如果当前页小于等于3
                lastPage = 5; // 前5页
                $scope.firstDot = false;// 前面没点
            } else if ($scope.searchMap.pageNo >= lastPage - 2) {// 如果当前页大于等于最大页码-2
                firstPage = maxPageNo - 4; // 后5页
                $scope.lastDot = false;// 后边没点
            } else { // 显示当前页为中心的5页
                firstPage = $scope.searchMap.pageNo - 2;
                lastPage = $scope.searchMap.pageNo + 2;
            }
        } else {
            $scope.firstDot = false;// 前面无点
            $scope.lastDot = false;// 后边无点
        }

        // 循环产生页码标签
        for (var i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }
    }

    // 根据页码查询
    $scope.queryByPage = function (pageNo) {
        // 页码验证
        if (pageNo < 1 || pageNo > $scope.resultMap.data.pages) {
            return;
        }
        $scope.searchMap.pageNo = pageNo;
        $scope.search2();
    }


    //判断当前页为第一页
    $scope.isTopPage = function () {
        if ($scope.searchMap.pageNo == 1) {
            return true;
        } else {
            return false;
        }
    }
    //判断当前页是否未最后一页
    $scope.isEndPage = function () {
        if ($scope.searchMap.pageNo == $scope.resultMap.totalPages) {
            return true;
        } else {
            return false;
        }
    }

    //设置排序规则
    $scope.sortSearch = function (sortField, sort) {
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sort = sort;
        $scope.search2();
    }

    //判断关键字是不是品牌
    $scope.keywordsIsBrand = function () {
        for (var i = 0; i < $scope.resultMap.brandList.length; i++) {
            if ($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text) >= 0) {//如果包含
                return true;
            }
        }
        return false;
    }
    //加载查询字符串,接受从content-service的index.html的搜索框传来的关键字
    $scope.loadkeywords = function () {
        $scope.key = $location.search()['key'];
        console.log($scope.key);
        $scope.search();
    }

    $scope.logout = function () {
        sessionStorage.clear();
    }



});