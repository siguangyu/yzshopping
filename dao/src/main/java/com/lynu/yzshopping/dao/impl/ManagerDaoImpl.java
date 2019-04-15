package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.ManagerDao;
import com.lynu.yzshopping.mybatis.entity.Manager;
import com.lynu.yzshopping.mybatis.persistence.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository(value = "managerDao")
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    ManagerMapper managerMapper;
    @Override
    public List<Manager> selectByConditionMap(Map<String, Object> map) {
        return managerMapper.selectByConditionMap(map);
    }
}
