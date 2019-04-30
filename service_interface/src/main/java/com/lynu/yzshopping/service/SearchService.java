package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.Record;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Record> selectByConditionMap(Map<String, Object> map) ;


    List<String> loadDomainBySearchKey(String searchKey);
}
