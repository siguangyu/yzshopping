package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.UserGoodMapping;

public interface UserGoodMappingDao {
    int updateByPrimaryKey(UserGoodMapping record);

    int insert(UserGoodMapping record);
}
