package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.User;

import java.util.Map;

public interface UserDao {

    User selectByPrimaryKey(Integer id);

    User selectByConditionMap(Map<String,Object> map);

}
