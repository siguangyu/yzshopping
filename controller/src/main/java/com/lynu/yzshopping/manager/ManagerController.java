package com.lynu.yzshopping.manager;

import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.mybatis.entity.Manager;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.service.ManagerService;
import com.lynu.yzshopping.util.Md5Util;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "管理员接口", tags = "管理员接口")
@RestController
@RequestMapping("manager")
public class ManagerController {
    Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    ManagerService managerService;

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("login")
    public Result loginUser(@RequestBody String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);
        String account = json.getString("account");
        String password = json.getString("password");
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        List<Manager> managerList = managerService.selectByConditionMap(map);
        if (managerList.size() == 1) {
            Manager manager = managerList.get(0);
            Map<String, Object> backMap = new HashMap<>();
            backMap.put("managerId", manager.getId());
            backMap.put("managerName", manager.getUsername());
            return ResultHandle.getSuccessResult("登录成功").setData(backMap);
        } else {
            return ResultHandle.getFailResult("账户名或密码错误");
        }

    }
}
