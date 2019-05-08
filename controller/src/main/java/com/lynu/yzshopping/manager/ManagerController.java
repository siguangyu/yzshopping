package com.lynu.yzshopping.manager;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.lynu.yzshopping.mybatis.entity.*;
import com.lynu.yzshopping.service.ManagerService;
import com.lynu.yzshopping.service.ScoreShopService;
import com.lynu.yzshopping.service.UserGoodMappingService;
import com.lynu.yzshopping.service.UserService;
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
import java.util.ArrayList;
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

    @Autowired
    UserGoodMappingService userGoodMappingService;

    @Autowired
    UserService userService;

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
        String gTitle = (String) json.get("gTitle");
        String gImageUrl = (String) json.get("gImageUrl");
        String gPrice = (String) json.get("gPrice");
        String gNumber = (String) json.get("gNumber");
        String gDelete = (String) json.get("gDelete");
        if (StringUtils.isBlank(gDelete)) {
            gDelete = YZConstants.XIAJIA_NO + "";
        }

        ScoreShop scoreShop = new ScoreShop();
        scoreShop.setgTitle(gTitle);
        scoreShop.setgImageUrl(gImageUrl);
        scoreShop.setgPrice(gPrice);
        scoreShop.setgNumber(gNumber);
        scoreShop.setgDelete(gDelete);
//添加商品
        int i = scoreShopService.insert(scoreShop);

        if (i == 1) {
            return ResultHandle.getSuccessResult("添加成功");
        } else {
            return ResultHandle.getFailResult("添加失败");
        }

    }

    @ApiOperation(value = "根据订单状态查询", notes = "根据订单状态查询")
    @PostMapping("selectOrderByCondition")
    public Result selectOrderByCondition(@RequestBody(required = false) String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);

        String transactionStatus = (String) json.get("transactionStatus");
        if (StringUtils.isBlank(transactionStatus)){
            transactionStatus=null;
        }
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        map.put("transactionStatus", transactionStatus);

        List<UserGoodMapping> userGoodMappingList = userGoodMappingService.selectByConditionMap(map);
        //根据用户id获取到用户的用户名
        for (UserGoodMapping userGoodMapping : userGoodMappingList) {
            Integer userId=userGoodMapping.getUserId();
            User user = userService.selectByPrimaryKey(userId);
            Integer goodsId=userGoodMapping.getGoodsId();
            ScoreShop scoreShop = scoreShopService.selectByPrimaryKey(goodsId);
            Map<String,Object> resMap=new HashMap<>();
            resMap.put("id",userGoodMapping.getId());
            resMap.put("gImageUrl",scoreShop.getgImageUrl());
            resMap.put("gTitle",scoreShop.getgTitle());
            resMap.put("userName", user.getUsername());
            resMap.put("createTime",userGoodMapping.getCreateTime());
            String status=userGoodMapping.getTransactionStatus().equals(YZConstants.TRANING_STATUS+"")?"待处理":"处理完成";
            resMap.put("status",status);
            list.add(resMap);
        }
        return ResultHandle.getSuccessResult().setData(list);
    }

    @ApiOperation(value = "根据映射表id更改交易状态", notes = "根据映射表id更改交易状态")
    @PostMapping("updateByOrderId")
    public Result updateByOrderId(@RequestBody Integer id) {

//        JSONObject json = JSONObject.parseObject(jsonBody);

//        String idStr = (String) json.get("id");

        String transactionStatus ="";
        UserGoodMapping userGoodMapping = userGoodMappingService.selectByPrimaryKey(id);
        if (userGoodMapping.getTransactionStatus().equals(YZConstants.TRANING_STATUS+"")){
            transactionStatus="1";
        }

        if (userGoodMapping.getTransactionStatus().equals(YZConstants.TRAN_OK_STATUS+"")){
            transactionStatus="0";
        }
        userGoodMapping.setTransactionStatus(transactionStatus);
        try {
            userGoodMappingService.updateByPrimaryKeySelective(userGoodMapping);
            return ResultHandle.getSuccessResult("修改成功");
        } catch (Exception e) {
            return ResultHandle.getFailResult("修改失败");
        }


    }

}
