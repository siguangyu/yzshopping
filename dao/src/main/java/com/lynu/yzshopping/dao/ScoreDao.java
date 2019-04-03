package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreDao {
    List<Score> selectByConditionMap(Map<String,Object> map);

    int insert(Score record);
}
