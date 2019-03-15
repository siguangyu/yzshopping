package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.User;

import java.util.Map;

public interface UserService {

    User selectByPrimaryKey(Integer id);

    User selectByConditionMap(Map<String,Object> map);
}
