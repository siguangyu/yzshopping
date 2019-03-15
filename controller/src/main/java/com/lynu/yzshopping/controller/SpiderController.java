package com.lynu.yzshopping.controller;


import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.service.impl.BiBiJing;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: data-interface
 * @description: 比比鲸控制层
 * @author: houyu
 * @create: 2018-12-10 09:25
 */
@Api(value = "获取数据接口",tags = "获取数据接口")
@RestController
@RequestMapping("/data-interface")
public class SpiderController {

    @Autowired
    BiBiJing biBiJing;


    @ApiOperation(value = "根据关键字和页码查询",notes = "根据关键字和页码查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "关键字",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",required = false,dataType = "int",paramType = "query")
    })
    @GetMapping("/bijia")
    public Result biJia(@RequestParam(value = "key", defaultValue = "") String key, @RequestParam(value = "page", defaultValue = "1") int pageNum) {
        // System.out.println("---------->>biJia" + key + "==>>" + pageNum);
        if ("".equals(key)) return ResultHandle.getFailResult("key can not be empty");


        Map<String, Object> process = biBiJing.process(key, pageNum);
        return ResultHandle.getSuccessResult().setData(process);
    }
}
