package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.dao.RecordDao;
import com.lynu.yzshopping.mybatis.entity.Record;
import com.lynu.yzshopping.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("searchService")
public class SearchServiceImpl implements SearchService {
    @Autowired
    RecordDao recordDao;
    @Override
    public List<Record> selectByConditionMap(Map<String, Object> map) {
        return recordDao.selectByConditionMap(map);
    }

    @Override
    public List<String> loadDomainBySearchKey(String searchKey) {
        return recordDao.loadDomainBySearchKey(searchKey);
    }
}
