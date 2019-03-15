package com.lynu.yzshopping.controller;


import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.util.CommonUtil;
import com.lynu.yzshopping.util.FixedCache;
import com.lynu.yzshopping.util.HttpUtil;
import com.lynu.yzshopping.util.ResultHandle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: data-interface
 * @description:
 * @author: houyu
 * @create: 2019-01-30 17:41
 */

@RestController
@RequestMapping("/data-interface")
public class ProxyPoolController {

    private CommonUtil commonUtil = CommonUtil.get();
    private FixedCache<String> fixedCache = new FixedCache(50);
    private Long endUpdateTime = 0L;


    @GetMapping("/proxypool")
    public Result proxypool(@RequestParam(value = "size", defaultValue = "20") int size) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - endUpdateTime > 1000 * 8) {
            for (int i = 1; i < 5; i++){
                String html = HttpUtil.get().getHtml("http://ip.jiangxianli.com/?page=" + i);
                List list = CommonUtil.get().regexMatcher(html, "data-url=\"(.*)\"\\s*data-unique-id=", List.class);
                if (list.size() == 0){
                    break;
                }
                fixedCache.addAll(list);
            }
            endUpdateTime = currentTimeMillis;
        }
        List<String> dataList = fixedCache.toList().subList(0, size > 49 ? 49 : size);
        return ResultHandle.getSuccessResult("代理IP采集于网络，请勿用于非法途径，违者后果自负！").setData(dataList);
    }
}
