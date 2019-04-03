package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.ScoreDao;
import com.lynu.yzshopping.mybatis.entity.Score;
import com.lynu.yzshopping.mybatis.persistence.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository(value = "scoreDao")
public class ScoreDaoImpl implements ScoreDao {
    @Autowired
    ScoreMapper scoreMapper;
    @Override
    public List<Score> selectByConditionMap(Map<String, Object> map) {
        return scoreMapper.selectByConditionMap(map);
    }

    @Override
    public int insert(Score record) {
        return scoreMapper.insert(record);
    }
}
