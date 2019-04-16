package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.ScoreShopDao;
import com.lynu.yzshopping.mybatis.entity.ScoreShop;
import com.lynu.yzshopping.mybatis.persistence.ScoreShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository(value = "scoreShopDao")
public class ScoreShopDaoImpl implements ScoreShopDao {

    @Autowired
    ScoreShopMapper scoreShopMapper;
    @Override
    public List<ScoreShop> selectByConditionMap(Map<String, Object> map) {
        return scoreShopMapper.selectByConditionMap(map);
    }

    @Override
    public List<ScoreShop> selectByScore(Map<String, Object> map) {
        return scoreShopMapper.selectByScore(map);
    }

    @Override
    public List<ScoreShop> selectAllByScoreAsc(Map<String, Object> map) {
        return scoreShopMapper.selectAllByScoreAsc(map);
    }

    @Override
    public int updateByPrimaryKey(ScoreShop record) {
        return scoreShopMapper.updateByPrimaryKey(record);
    }

    @Override
    public ScoreShop selectByPrimaryKey(Integer id) {
        return scoreShopMapper.selectByPrimaryKey(id);
    }
}
