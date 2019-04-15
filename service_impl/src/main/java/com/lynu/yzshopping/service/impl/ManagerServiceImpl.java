package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.dao.ManagerDao;
import com.lynu.yzshopping.mybatis.entity.Manager;
import com.lynu.yzshopping.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service(value = "managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;
    @Override
    public List<Manager> selectByConditionMap(Map<String, Object> map) {
        return managerDao.selectByConditionMap(map);
    }
}
