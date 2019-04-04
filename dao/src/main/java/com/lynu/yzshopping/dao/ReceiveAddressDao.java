package com.lynu.yzshopping.dao;

import com.lynu.yzshopping.mybatis.entity.ReceiveAddress;

import java.util.List;
import java.util.Map;

public interface ReceiveAddressDao {

    int insertSelective(ReceiveAddress record);

    List<ReceiveAddress> selectByConditionMap(Map<String,Object> map);


    int updateByPrimaryKeySelective(ReceiveAddress record);

    int deleteByPrimaryKey(Integer id);
}
