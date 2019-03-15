package com.lynu.yzshopping.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: data-interface
 * @description:
 * @author: houyu
 * @create: 2019-01-30 18:15
 */
public class FixedCache<E>{

    private List<E> dataList;
    private int size;

    public FixedCache(int size){
        this.dataList  = new ArrayList<>(size);
        this.size = size;
    }

    public void add(E e){
        dataList.add(0, e);
        if (dataList.size() > this.size){
            dataList.remove(this.size);
        }
    }

    public void addAll(Collection<? extends E> es){
        for (E e : es){
            add(e);
        }
    }

    public List<E> toList(){
        return dataList;
    }

    @Override
    public String toString() {
        return dataList.toString();
    }
}
