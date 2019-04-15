package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerDao {

    List<Manager> selectByConditionMap(Map<String,Object> map);
}
