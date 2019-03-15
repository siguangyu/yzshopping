package com.lynu.yzshopping.controller;

import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.mybatis.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "用户接口",tags = "用户接口")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation(value = "根据id查询用户",notes = "根据id查询用户")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "int",paramType = "query")
    })
    @PostMapping("selectByPrimaryKey")
public User selectByPrimaryKey(@RequestParam Integer id){
        return userService.selectByPrimaryKey(id);
    }

}
