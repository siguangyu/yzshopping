package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    List<Score> selectByConditionMap(Map<String,Object> map);

    int insert(Score record);
}
