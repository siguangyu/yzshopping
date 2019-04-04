package com.lynu.yzshopping.service;

import com.lynu.yzshopping.mybatis.entity.ReceiveAddress;

import java.util.List;
import java.util.Map;

public interface ReceiveAddressService {
    int insertSelective(ReceiveAddress receiveAddress);

    List<ReceiveAddress> selectByConditionMap(Map<String,Object> map);

    int updateByPrimaryKeySelective(ReceiveAddress record);

    int deleteByPrimaryKey(Integer id);
}
