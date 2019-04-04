package com.lynu.yzshopping.controller;

import com.lynu.yzshopping.mybatis.entity.ReceiveAddress;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.service.ReceiveAddressService;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "收获地址接口", tags = "收获地址接口")
@RestController
@RequestMapping("address")
public class ReceiveAddressController {
    Logger logger = LoggerFactory.getLogger(ReceiveAddressController.class);
    @Autowired
    ReceiveAddressService receiveAddressService;



/*
    * 新增收货地址
    * */
    @ApiOperation(value = "新增收货地址", notes = "新建收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "receiveName", value = "收件人姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "receivePhone", value = "收件人联系电话", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "province", value = "省", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "city", value = "市", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "county", value = "县", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "addressDetail", value = "详细地址", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("insertReceiveAddress")
    public Result insertReceiveAddress(HttpServletRequest request) {



        String userId = request.getParameter("userId");
        String receiveName = request.getParameter("receiveName");
        String receivePhone = request.getParameter("receivePhone");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String addressDetail = request.getParameter("addressDetail");

        ReceiveAddress receiveAddress=new ReceiveAddress();
        receiveAddress.setUserId(Integer.parseInt(userId));
        receiveAddress.setReceiveName(receiveName);
        receiveAddress.setReceivePhone(receivePhone);
        receiveAddress.setProvince(province);
        receiveAddress.setCity(city);
        receiveAddress.setCounty(county);
        receiveAddress.setAddressDetail(addressDetail);
        receiveAddress.setCreateTime(new Date());
        int i=receiveAddressService.insertSelective(receiveAddress);
        if (i!=1){
            return ResultHandle.getFailResult();
        }
        return ResultHandle.getSuccessResult();
    }

    /*
    * 根据用户id查询收货地址
    * 场景：1.查询
    *       2. 修改时回显
    * */
    @ApiOperation(value = "查询收货地址", notes = "查询收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("selectAllReceiveAddress")
    public Result selectAllReceiveAddress(HttpServletRequest request) {

        String userId = request.getParameter("userId");
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        List<ReceiveAddress> receiveAddresseList = receiveAddressService.selectByConditionMap(map);
        return ResultHandle.getSuccessResult().setData(receiveAddresseList);
    }

    /*
  * 修改收货地址
  * */
    @ApiOperation(value = "修改收货地址", notes = "修改收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "receiveName", value = "收件人姓名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "receivePhone", value = "收件人联系电话", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "province", value = "省", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "city", value = "市", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "county", value = "县", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "addressDetail", value = "详细地址", required = false, dataType = "String", paramType = "query")

    })
    @PostMapping("updateReceiveAddress")
    public Result updateReceiveAddress(HttpServletRequest request) {
        String id = request.getParameter("id");
        String receiveName = request.getParameter("receiveName");
        String receivePhone = request.getParameter("receivePhone");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String addressDetail = request.getParameter("addressDetail");

        ReceiveAddress receiveAddress=new ReceiveAddress();
        receiveAddress.setId(Integer.parseInt(id));
        receiveAddress.setReceiveName(receiveName);
        receiveAddress.setReceivePhone(receivePhone);
        receiveAddress.setReceivePhone(receivePhone);
        receiveAddress.setProvince(province);
        receiveAddress.setCity(city);
        receiveAddress.setCounty(county);
        receiveAddress.setAddressDetail(addressDetail);
        receiveAddress.setUpdateTime(new Date());


        int i = receiveAddressService.updateByPrimaryKeySelective(receiveAddress);
        if (i!=1){
            return ResultHandle.getFailResult("修改失败");
        }
        return ResultHandle.getSuccessResult("修改成功");
    }
    /*
    * 删除收货地址
    * */
    @ApiOperation(value = "删除收货地址", notes = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("deleteReceiveAddress")
    public Result deleteReceiveAddress(HttpServletRequest request) {
        String id = request.getParameter("id");
        int i = receiveAddressService.deleteByPrimaryKey(Integer.parseInt(id));

        if (i!=1){
            return ResultHandle.getFailResult("删除失败");
        }
        return ResultHandle.getSuccessResult("删除成功");
    }

}
