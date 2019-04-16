package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.ScoreShop;

import java.util.List;
import java.util.Map;

public interface ScoreShopService {
    /*
   * 查询积分商城内符合条件的商品
   * */
    List<ScoreShop> selectByConditionMap(Map<String,Object> map);

    /*
    * 查询积分商城内符合条件的商品
    * */
    List<ScoreShop> selectByScore(Map<String,Object> map);

    /* 根据用户积分，查询”我能购买“*/
    List<ScoreShop> selectAllByScoreAsc(Map<String,Object> map);

    //收藏、兑换
    String SaveOrExchangeShop(Map<String,Object> map);

    ScoreShop selectByPrimaryKey(Integer id);

}
