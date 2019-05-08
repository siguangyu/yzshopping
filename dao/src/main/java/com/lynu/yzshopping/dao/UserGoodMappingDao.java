package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.UserGoodMapping;

import java.util.List;
import java.util.Map;

public interface UserGoodMappingDao {
    int updateByPrimaryKey(UserGoodMapping record);

    int insert(UserGoodMapping record);

    List<UserGoodMapping> selectByConditionMap(Map<String, Object> map) ;

    int updateByPrimaryKeySelective(UserGoodMapping record);

    UserGoodMapping selectByPrimaryKey(Integer id);
}
