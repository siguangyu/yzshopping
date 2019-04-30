package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.dao.RecordDao;
import com.lynu.yzshopping.mybatis.entity.Record;
import com.lynu.yzshopping.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("recordService")
public class RecordServiceImpl implements RecordService  {

    @Autowired
    RecordDao recordDao;
    @Override
    public int insert(Record record) {
        return recordDao.insert(record);
    }
}
