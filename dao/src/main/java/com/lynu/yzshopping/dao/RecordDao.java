package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.Record;

import java.util.List;
import java.util.Map;

public interface RecordDao {
    int insert(Record record);

    List<Record> selectByConditionMap(Map<String, Object> map) ;

    List<String> loadDomainBySearchKey(String searchKey);
}
