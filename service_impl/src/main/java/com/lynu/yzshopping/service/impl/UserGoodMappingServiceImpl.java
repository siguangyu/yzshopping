package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.dao.UserGoodMappingDao;
import com.lynu.yzshopping.mybatis.entity.UserGoodMapping;
import com.lynu.yzshopping.service.UserGoodMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2019/4/16$ 17:04$
 */
@Service(value = "userGoodMappingService")
public class UserGoodMappingServiceImpl implements UserGoodMappingService {

    @Autowired
    UserGoodMappingDao userGoodMappingDao;
    @Override
    public List<UserGoodMapping> selectByConditionMap(Map<String, Object> map) {
        return userGoodMappingDao.selectByConditionMap(map);
    }

    @Override
    public int updateByPrimaryKeySelective(UserGoodMapping record) {
        return userGoodMappingDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserGoodMapping selectByPrimaryKey(Integer id) {
        return userGoodMappingDao.selectByPrimaryKey(id);
    }
}
