package com.lynu.yzshopping.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lynu.yzshopping.mybatis.entity.Goods;
import com.lynu.yzshopping.service.constants.YZConstants;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.mybatis.entity.Score;
import com.lynu.yzshopping.mybatis.entity.ScoreShop;
import com.lynu.yzshopping.service.ScoreService;
import com.lynu.yzshopping.service.ScoreShopService;
import com.lynu.yzshopping.service.UserService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "积分商城相关接口", tags = "积分商城相关接口")
@RestController
@RequestMapping("user/scoreShop")
public class ScoreShopController {
    Logger logger = LoggerFactory.getLogger(ScoreShopController.class);

    @Autowired
    ScoreShopService scoreShopService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    UserService userService;


    @ApiOperation(value = "查询全部商品", notes = "查询全部商品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "int", paramType = "query"),
                    @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, dataType = "int", paramType = "query")
            }
    )
    @PostMapping("selectAll")
    public Result selectByPrimaryKey(HttpServletRequest request) {
        logger.info("====================条件查询开始====================");
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");
        int page = 1;
        int pageSize = 50;
        if (!StringUtils.isBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (!StringUtils.isBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //分页
        PageHelper.startPage(page, pageSize);
        //根据查询条件进行查询，传入一个空的map集合
        List<ScoreShop> scoreShopList = scoreShopService.selectByConditionMap(new HashMap<String, Object>());
        PageInfo<ScoreShop> pageInfo = new PageInfo<>(scoreShopList);
        return ResultHandle.getSuccessResult().setData(pageInfo);
    }


    @ApiOperation(value = "根据积分进行范围查询商品", notes = "根据积分进行范围查询商品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "int", paramType = "query"),
                    @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, dataType = "int", paramType = "query"),
                    @ApiImplicitParam(name = "score", value = "积分范围", required = true, dataType = "String", paramType = "query")
            }
    )
    @PostMapping("selectByScore")
    public Result selectByScore(HttpServletRequest request) {
        logger.info("====================根据积分进行范围查询======================");
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");
        String score = request.getParameter("score");

        int page = YZConstants.PAGE;
        int pageSize = YZConstants.PAGE_SIZE;
        if (!StringUtils.isBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (!StringUtils.isBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("score", score);

        PageHelper.startPage(page, pageSize);
        List<ScoreShop> scoreShopList = scoreShopService.selectByScore(map);
        //分页
        PageInfo<ScoreShop> pageInfo = new PageInfo<>(scoreShopList);
        return ResultHandle.getSuccessResult().setData(pageInfo);
    }


    /*
    * 查询小于自己积分的商品
    * */

    @ApiOperation(value = "查询小于自己积分的商品", notes = "查询小于自己积分的商品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "int", paramType = "query"),
                    @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, dataType = "int", paramType = "query"),
                    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
            }
    )
    @PostMapping("selectScoreShop")
    public Result selectScoreShop(HttpServletRequest request) {
        //获取用户积分总数
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");
        String userIdStr = request.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Score> scoreList = scoreService.selectByConditionMap(map);
        Score score = scoreList.get(scoreList.size() - 1);
        Integer scoreTotal = Integer.parseInt(score.getScoreTotal());
        //去积分商城表查询，小于这个积分的全部商品，查询结果根据积分升序排列
        int page = YZConstants.PAGE;
        int pageSize = YZConstants.PAGE_SIZE;
        if (!StringUtils.isBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (!StringUtils.isBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageHelper.startPage(page, pageSize);
        map.put("scoreTotal", scoreTotal);

        List<ScoreShop> scoreShopList = scoreShopService.selectAllByScoreAsc(map);
        //进行分页
        PageInfo<ScoreShop> pageInfo = new PageInfo<>(scoreShopList);
        return ResultHandle.getSuccessResult().setData(pageInfo);
    }

    @ApiOperation(value = "根据用户id查询积分总数", notes = "根据用户id查询积分总数")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query")
            }
    )
    @GetMapping("selectScoreByUserId")
    public Result selectScoreByUserId(HttpServletRequest request) {
        //获取用户积分总数
        String userIdStr = request.getParameter("id");
        int userId = Integer.parseInt(userIdStr);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Score> scoreList = scoreService.selectByConditionMap(map);
        Score score = scoreList.get(scoreList.size() - 1);
        Integer scoreTotal = Integer.parseInt(score.getScoreTotal());
        return ResultHandle.getSuccessResult().setData(scoreTotal);
    }
    /*
    * 积分兑换商品接口
    * */
    @ApiOperation(value = "积分兑换商品接口", notes = "积分兑换商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("score/exchange")
    public Result scoreExchange(HttpServletRequest request) {

        logger.info("====================积分兑换商品======================");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));

        Map<String, Object> map = new HashMap<>();
        map.put("status", YZConstants.EXCHANGE_STATUS);
        map.put("userId", userId);
        map.put("goodsId", goodsId);
        String res = scoreShopService.SaveOrExchangeShop(map);
        if (res.contains("成功")){
            return ResultHandle.getSuccessResult(res);
        }else{
            return ResultHandle.getFailResult(res);
        }
    }

    /*
      * 收藏爬取的商品接口
      * */
    @ApiOperation(value = "收藏爬取的商品接口", notes = "收藏爬取的商品接口")
    @PostMapping("save/good")
    public Result scoreExchange(@RequestBody String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);
        //把商品信息加入到goods表中
        JSONObject item = json.getJSONObject("item");
        Goods goods=new Goods();
        goods.setPictureUrl((String)item.get("imgUrl"));
        goods.setGoodName((String)item.get("title"));
        goods.setPrice((String)item.get("price"));
        goods.setGoodUrl((String) item.get("url"));
        goods.setCreateTime(new Date());




        return ResultHandle.getSuccessResult();
    }
}