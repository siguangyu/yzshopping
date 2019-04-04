package com.lynu.yzshopping.service.impl;

import com.lynu.yzshopping.dao.ReceiveAddressDao;
import com.lynu.yzshopping.mybatis.entity.ReceiveAddress;
import com.lynu.yzshopping.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "receiveAddressService")
public class ReceiveAddressServiceImpl implements ReceiveAddressService {

    @Autowired
    ReceiveAddressDao receiveAddressDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(ReceiveAddress receiveAddress) {
        return receiveAddressDao.insertSelective(receiveAddress);
    }

    @Override
    public List<ReceiveAddress> selectByConditionMap(Map<String, Object> map) {
        return receiveAddressDao.selectByConditionMap(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(ReceiveAddress record) {
        return receiveAddressDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return receiveAddressDao.deleteByPrimaryKey(id);
    }
}
