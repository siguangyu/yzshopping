package com.lynu.yzshopping.dao;


import com.lynu.yzshopping.mybatis.entity.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: data-interface
 * @description: 访问日志资源库
 * @author: houyu
 * @create: 2018-12-06 20:26
 */
@Repository
public interface VisitLogMapper extends JpaRepository<VisitLog, String> {

}
