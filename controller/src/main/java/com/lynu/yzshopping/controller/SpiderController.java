package com.lynu.yzshopping.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lynu.yzshopping.mybatis.entity.Record;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.service.RecordService;
import com.lynu.yzshopping.service.SearchService;
import com.lynu.yzshopping.service.impl.BiBiJing;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @program: data-interface
 * @description: 比比鲸控制层
 * @author: houyu
 * @create: 2018-12-10 09:25
 */
@Api(value = "获取数据接口", tags = "获取数据接口")
@RestController
@RequestMapping("/data-interface")
public class SpiderController {

    @Autowired
    BiBiJing biBiJing;
    @Autowired
    RecordService recordService;
    @Autowired
    SearchService searchService;


    @ApiOperation(value = "根据关键字和页码查询", notes = "根据关键字和页码查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "关键字", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = false, dataType = "int", paramType = "query")
    })
    @GetMapping("/bijia")
    public Result biJia(@RequestParam(value = "key", defaultValue = "") String key, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        // System.out.println("---------->>biJia" + key + "==>>" + pageNum);
        if ("".equals(key) || "undefined".equals(key))
            return ResultHandle.getFailResult("key can not be empty");
      /*  Map<String, Object> process = biBiJing.process(key, pageNo);
        return ResultHandle.getSuccessResult().setData(process);*/
        List<Map<String, Object>> pageList = new ArrayList<>();
        for (int i = pageNo; i <= 5; i++) {
            Map<String, Object> process = biBiJing.process(key, pageNo);
            pageList = (List) process.get("page");

            if (pageList.get(0) != null || pageNo <= 5) {
                for (Map<String, Object> map : pageList) {
                    Record record = new Record();
                    record.setSearchKey(key);
                    record.setTitle((String) map.get("title"));
                    record.setServiceUrl((String) map.get("serviceurl"));
                    record.setpUrl((String) map.get("purl"));
                    record.setUrl((String) map.get("url"));
                    record.setImgUrl((String) map.get("imgUrl"));
                    record.setPrice(Float.parseFloat((String) map.get("price")));
                    record.setDomain((String) map.get("domain"));
                    record.setDomainch((String) map.get("domainCh"));
                    record.setBrand((String) map.get("brand"));
                    record.setService((String) map.get("service"));
                    record.setComnum((Integer) map.get("comnum"));
                    record.sethKey((String) map.get("hkey"));
                    record.setRowKey((String) map.get("rowkey"));
                    record.setGroupRowKey((String) map.get("groupRowKey"));
                    record.setCreateTime(new Date());
                    try {
                        int num = recordService.insert(record);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchKey", key);
        searchMap.put("pageNo", pageNo);
        return search(searchMap);
    }

    ;


    @RequestMapping("/search")
    public Result search(@RequestBody Map searchMap) {
        Integer pageNo = (Integer) searchMap.get("pageNo");
        String searchKey = (String) searchMap.get("searchKey");
        PageHelper.startPage(pageNo, 50);
        List<Record> list = searchService.selectByConditionMap(searchMap);
        //如果数据库查不到，就使用爬虫方法
        if (list.size() == 0) {
            biJia(searchKey, pageNo);
        }
        PageInfo<Record> pageInfo = new PageInfo<>(list);
        System.out.println(ResultHandle.getSuccessResult().setData(pageInfo).toString());
        return ResultHandle.getSuccessResult().setData(pageInfo);
    }



    @GetMapping("/loaddomain")
    public Result loaddomain(@RequestParam String searchKey) {
        List<String> resultList = searchService.loadDomainBySearchKey(searchKey);
        return ResultHandle.getSuccessResult().setData(resultList);
    }

}
