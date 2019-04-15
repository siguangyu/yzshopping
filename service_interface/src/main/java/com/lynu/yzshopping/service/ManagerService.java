package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Manager> selectByConditionMap(Map<String,Object> map);
}
