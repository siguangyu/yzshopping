package com.lynu.yzshopping.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

/**
 * @program: data-interface
 * @description:
 * @author: houyu
 * @create: 2018-12-06 20:35
 */
public interface BaseService<E> {


    /**
     * 增加单个数据
     *
     * @param e
     * @return
     */
    E save(E e);

    /**
     * 修改单个数据
     *
     * @param e
     */
    E updata(E e);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<E> findAll(@PageableDefault Pageable pageable);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<E> findAll();

    /**
     * 根据id查找单个数据
     *
     * @param id
     * @return
     */
    E findById(String id);

    /**
     * 删除所有数据
     */
    void deleteAll();

    /**
     * 根据id删除单个对象
     *
     * @param id
     */
    void deleteById(String id);

}

