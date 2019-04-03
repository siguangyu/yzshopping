package com.lynu.yzshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "用户接口", tags = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "query")
    })
    @PostMapping("selectByPrimaryKey")
    public User selectByPrimaryKey(@RequestParam Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("register")
    public Result registerUser(@RequestBody String jsonBody) {
        try {
            //返回值为自增主键id
            int i = userService.insertSelective(jsonBody);
            if (i != 0) {
                User user = userService.selectByPrimaryKey(i);
                Map<String,Object> map=new HashMap<>();
                map.put("userId",i);
                map.put("userName",user.getUsername());
                map.put("account",user.getAccount());
                return ResultHandle.getSuccessResult("注册成功").setData(map);
            } else {
                return ResultHandle.getFailResult("注册失败");
            }
        } catch (Exception e) {
            return ResultHandle.getFailResult("系统错误");
        }
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("login")
    public Result loginUser(@RequestBody String jsonBody){
        JSONObject json = JSONObject.parseObject(jsonBody);
        String account = json.getString("account");
        String password = json.getString("password");
        Map<String,Object> map=new HashMap<>();
        map.put("account",account);
        map.put("password",password);
        List<User> userList = userService.selectByConditionMap(map);
        if (userList.size()==1){
            User user = userList.get(0);
            Map<String,Object> backMap=new HashMap<>();
            backMap.put("userId",user.getId());
            backMap.put("userName",user.getUsername());
            return ResultHandle.getSuccessResult("登录成功").setData(backMap);
        }else{
            return ResultHandle.getFailResult("账户名或密码错误");
        }


    }

    @ApiOperation(value = "用户基本信息修改接口", notes = "用户基本信息修改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("updateUserInfo")
    public Result updateUserInfo(HttpServletRequest request) {
        String id = request.getParameter("id");



        return ResultHandle.getSuccessResult("修改成功");
    }


}
