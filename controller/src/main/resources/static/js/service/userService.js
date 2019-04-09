//服务层
app.service('userService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../user/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../user/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.selectByPrimaryKey=function(id){
		return $http.get('../user/selectByPrimaryKey?id='+id);
	}
	//注册,添加用户
	this.add=function(entity){
		// alert(JSON.stringify(entity));
		return  $http.post('../user/register',entity );
	}
    //用户登录
    this.login=function(entity){
        // alert(JSON.stringify(entity));
        return  $http.post('../user/login',entity );
    }
	//修改 
	this.updateUserInfo=function(entity){
    	console.log(entity);
		return  $http.post('../user/updateUserInfo',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../user/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../user/search.do?page='+page+"&rows="+rows, searchEntity);
	}    	
	//发送验证码
	/*this.sendCode=function(phone){
		return $http.get("../user/sendCode.do?phone="+phone);
	}*/
	
	

});
