package com.lynu.yzshopping.manager;

import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.mybatis.entity.Manager;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.mybatis.entity.ScoreShop;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.service.ManagerService;
import com.lynu.yzshopping.service.ScoreShopService;
import com.lynu.yzshopping.service.constants.YZConstants;
import com.lynu.yzshopping.util.Md5Util;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ScoreShopService scoreShopService;

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

    @ApiOperation(value = "添加商品", notes = "添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gTitle", value = "商品名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gImageUrl", value = "图片URL", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gPrice", value = "积分商品所需积分", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gNumber", value = "商品数量", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gDelete", value = "是否下架", required = false, dataType = "String", paramType = "query")
    })
    @PostMapping("addGood")
    public Result addGood(@RequestBody String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);
        String gTitle = (String)json.get("gTitle");
        String gImageUrl = (String)json.get("gImageUrl");
        String gPrice = (String)json.get("gPrice");
        String gNumber = (String)json.get("gNumber");
        String gDelete = (String)json.get("gDelete");
        if (StringUtils.isBlank(gDelete)){
            gDelete= YZConstants.XIAJIA_NO+"";
        }

        ScoreShop scoreShop=new ScoreShop();
        scoreShop.setgTitle(gTitle);
        scoreShop.setgImageUrl(gImageUrl);
        scoreShop.setgPrice(gPrice);
        scoreShop.setgNumber(gNumber);
        scoreShop.setgDelete(gDelete);
//添加商品
        int i = scoreShopService.insert(scoreShop);

        if (i==1){
            return ResultHandle.getSuccessResult("添加成功");
        }else{
            return ResultHandle.getFailResult("添加失败");
        }

    }
}
