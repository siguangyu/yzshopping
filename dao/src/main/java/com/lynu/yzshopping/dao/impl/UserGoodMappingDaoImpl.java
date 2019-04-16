package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.UserGoodMappingDao;
import com.lynu.yzshopping.mybatis.entity.UserGoodMapping;
import com.lynu.yzshopping.mybatis.persistence.UserGoodMappingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "userGoodMappingDao")
public class UserGoodMappingDaoImpl implements UserGoodMappingDao {
    @Autowired
    UserGoodMappingMapper userGoodMappingMapper;
    @Override
    public int updateByPrimaryKey(UserGoodMapping record) {
        return userGoodMappingMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insert(UserGoodMapping record) {
        return userGoodMappingMapper.insert(record);
    }

    @Override
    public List<UserGoodMapping> selectByConditionMap(Map<String, Object> map) {
        return userGoodMappingMapper.selectByConditionMap(map);
    }
}
