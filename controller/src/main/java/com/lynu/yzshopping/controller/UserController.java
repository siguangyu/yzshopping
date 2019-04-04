package com.lynu.yzshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.util.Md5Util;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Api(value = "用户接口", tags = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("register")
    public Result registerUser(@RequestBody String jsonBody) {
        try {
            //返回值为自增主键id
            int i = userService.insertSelective(jsonBody);
            if (i != 0) {
                User user = userService.selectByPrimaryKey(i);
                Map<String, Object> map = new HashMap<>();
                map.put("userId", i);
                map.put("userName", user.getUsername());
                map.put("account", user.getAccount());
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
    public Result loginUser(@RequestBody String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);
        String account = json.getString("account");
        String password = Md5Util.EncoderByMd5(json.getString("password"));
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        List<User> userList = userService.selectByConditionMap(map);
        if (userList.size() == 1) {
            User user = userList.get(0);
            Map<String, Object> backMap = new HashMap<>();
            backMap.put("userId", user.getId());
            backMap.put("userName", user.getUsername());
            return ResultHandle.getSuccessResult("登录成功").setData(backMap);
        } else {
            return ResultHandle.getFailResult("账户名或密码错误");
        }


    }

    /*
    * 查询用户信息
    * 使用场景：用户信息修改的时候进行回显
    * */
    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("selectByPrimaryKey")
    public Result selectByPrimaryKey(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);

        User user = userService.selectByPrimaryKey(id);
        return ResultHandle.getSuccessResult().setData(user);
    }


    @ApiOperation(value = "用户基本信息修改接口", notes = "用户基本信息修改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "birthday", value = "生日", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "qq", value = "QQ号码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "mail", value = "邮箱", required = false, dataType = "String", paramType = "query")
    })
    @PostMapping("updateUserInfo")
    public Result updateUserInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        String birthday = request.getParameter("birthday");  //生日的格式  暂定为1996-12-12
        String qq = request.getParameter("qq");
        String mail = request.getParameter("mail");

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setQq(qq);
        user.setPhoneNumber(phoneNumber);
        user.setMail(mail);
        user.setUpdateTime(new Date());

        String res = userService.updateByPrimaryKeySelective(user);
        if (res.contains("成功")) {
            return ResultHandle.getSuccessResult(res);
        } else {
            return ResultHandle.getFailResult(res);
        }
    }


    @ApiOperation(value = "安全信息修改接口", notes = "安全信息修改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldPassword", value = "原来的密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "query")

    })
    @PostMapping("updateUserSafeInfo")
    public Result updateUserSafeInfo(HttpServletRequest request) {

        String id = request.getParameter("id");
        String account = request.getParameter("account");
        String oldPassword = Md5Util.EncoderByMd5(request.getParameter("oldPassword"));
        String newPassword = Md5Util.EncoderByMd5(request.getParameter("newPassword"));

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("account", account);
        map.put("password", oldPassword);
        List<User> userList = userService.selectByConditionMap(map);
        if (userList.size() != 1) {
            return ResultHandle.getFailResult("原密码错误");
        }
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setAccount(account);
        user.setPassword(newPassword);
        user.setUpdateTime(new Date());
        String res = userService.updateByPrimaryKeySelective(user);
        if (res.contains("成功")) {
            return ResultHandle.getSuccessResult(res);
        } else {
            return ResultHandle.getFailResult(res);
        }
    }
}