package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User selectByPrimaryKey(Integer id);

    List<User> selectByConditionMap(Map<String,Object> map);
//   根据非空字段添加用户
    int insertSelective(String jsonBody);
}
