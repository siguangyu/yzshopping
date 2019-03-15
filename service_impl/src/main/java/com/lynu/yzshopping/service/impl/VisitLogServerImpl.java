package com.lynu.yzshopping.service.impl;


import com.lynu.yzshopping.mybatis.entity.VisitLog;
import com.lynu.yzshopping.dao.VisitLogMapper;
import com.lynu.yzshopping.service.VisitLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: data-interface
 * @description:
 * @author: houyu
 * @create: 2018-12-06 20:57
 */
@Service
public class VisitLogServerImpl implements VisitLogServer {

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Override
    public VisitLog save(VisitLog visitLog) {
        return visitLogMapper.save(visitLog);
    }

    @Override
    public VisitLog updata(VisitLog visitLog) {
        return visitLogMapper.save(visitLog);
    }

    @Override
    public Page<VisitLog> findAll(Pageable pageable) {
//        Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createTime");
//        if (pageable.getSort() == null) {
//            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
//        }
        return visitLogMapper.findAll(pageable);
    }

    @Override
    public List<VisitLog> findAll() {
        return visitLogMapper.findAll();
    }

    @Override
    public VisitLog findById(String id) {
        return visitLogMapper.findById(id).orElse(null);
    }

    @Override
    public void deleteAll() {
        visitLogMapper.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        visitLogMapper.deleteById(id);
    }

}
