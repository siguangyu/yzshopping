package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.RecordDao;
import com.lynu.yzshopping.mybatis.entity.Record;
import com.lynu.yzshopping.mybatis.persistence.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("recordDao")
public class RecordDaoImpl implements RecordDao {

    @Autowired
    RecordMapper recordMapper;
    @Override
    public int insert(Record record) {
        return recordMapper.insert(record);
    }

     public List<Record> selectByConditionMap(Map<String, Object> map){
        return recordMapper.selectByConditionMap(map);
    }

    @Override
    public List<String> loadDomainBySearchKey(String searchKey) {
        return recordMapper.loadDomainBySearchKey(searchKey);
    }


}
