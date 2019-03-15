package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.dao.UserDao;
import com.lynu.yzshopping.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User selectByConditionMap(Map<String, Object> map) {
        return userDao.selectByConditionMap(map);
    }
}
