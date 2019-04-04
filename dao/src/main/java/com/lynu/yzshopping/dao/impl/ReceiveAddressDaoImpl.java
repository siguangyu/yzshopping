package com.lynu.yzshopping.dao.impl;

import com.lynu.yzshopping.dao.ReceiveAddressDao;
import com.lynu.yzshopping.mybatis.entity.ReceiveAddress;
import com.lynu.yzshopping.mybatis.persistence.ReceiveAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "receiveAddressDao")
public class ReceiveAddressDaoImpl implements ReceiveAddressDao {

    @Autowired
    ReceiveAddressMapper receiveAddressMapper;
    @Override
    public int insertSelective(ReceiveAddress record) {
        return receiveAddressMapper.insertSelective(record);
    }

    @Override
    public List<ReceiveAddress> selectByConditionMap(Map<String, Object> map) {
        return receiveAddressMapper.selectByConditionMap(map);
    }

    @Override
    public int updateByPrimaryKeySelective(ReceiveAddress record) {
        return receiveAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return receiveAddressMapper.deleteByPrimaryKey(id);
    }
}
