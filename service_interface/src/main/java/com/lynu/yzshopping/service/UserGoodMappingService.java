package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.Score;
import com.lynu.yzshopping.mybatis.entity.UserGoodMapping;

import java.util.List;
import java.util.Map;

public interface UserGoodMappingService {
    List<UserGoodMapping> selectByConditionMap(Map<String, Object> map);


}
