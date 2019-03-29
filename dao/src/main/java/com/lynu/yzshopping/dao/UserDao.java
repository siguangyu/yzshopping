package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    User selectByPrimaryKey(Integer id);

    List<User> selectByConditionMap(Map<String,Object> map);

//    根据非空字段添加用户
    int insertSelective(User record);



}
