package com.lynu.yzshopping.mybatis.persistence;

import com.lynu.yzshopping.mybatis.entity.ScoreShop;

import java.util.List;
import java.util.Map;

public interface ScoreShopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score_shop
     *
     * @mbggenerated Mon Apr 01 11:16:33 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score_shop
     *
     * @mbggenerated Mon Apr 01 11:16:33 CST 2019
     */
    int insert(ScoreShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score_shop
     *
     * @mbggenerated Mon Apr 01 11:16:33 CST 2019
     */
    int insertSelective(ScoreShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score_shop
     *
     * @mbggenerated Mon Apr 01 11:16:33 CST 2019
     */
    ScoreShop selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score_shop
     *
     * @mbggenerated Mon Apr 01 11:16:33 CST 2019
     */
    int updateByPrimaryKeySelective(ScoreShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score_shop
     *
     * @mbggenerated Mon Apr 01 11:16:33 CST 2019
     */
    int updateByPrimaryKey(ScoreShop record);


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

}