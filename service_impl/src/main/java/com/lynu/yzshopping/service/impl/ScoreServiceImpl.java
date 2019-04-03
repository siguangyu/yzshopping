package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.dao.ScoreDao;
import com.lynu.yzshopping.mybatis.entity.Score;
import com.lynu.yzshopping.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Override
    public List<Score> selectByConditionMap(Map<String, Object> map) {
        return scoreDao.selectByConditionMap(map);
    }

    @Override
    public int insert(Score record) {
        return scoreDao.insert(record);
    }
}
