package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.UserDao;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.mybatis.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @Autowired
    UserMapper userMapper;
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByConditionMap(Map<String, Object> map) {
        return userMapper.selectByConditionMap(map);
    }
}
