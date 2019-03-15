package com.lynu.yzshopping.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;

/**
 * @program: testutils
 * @description: 简单定时字典
 * @author: houyu
 * @create: 2018-12-05 00:02
 */
public class SimpleTimeCache {

    transient int      thresholdSize    = 500;                                             // 阈值      :500
    transient int      overTime         = 1000 * 10;                                       // 过时      :10秒(1000 * 10)
    transient boolean  isSync           = false;                                           // 是否同步  :不同步

    private final Lock lock             = new ReentrantLock();                             // 同步锁对象

    private volatile Map<String, Long>   timeHashMap         = new ConcurrentHashMap<>();  // 时间Map
    private volatile Map<String, Object> valueLinkedHashMap  = new LinkedHashMap<>();      // 存储Map
    private volatile Long                LastValueTime       = 0L;                         // 最后一个添加的时间

    public SimpleTimeCache() {
    }

    public SimpleTimeCache(int thresholdSize, int overTime){
        this.thresholdSize = thresholdSize;
        this.overTime = overTime;
    }

    // 同步添加
    private void putBySync(Map map, String key, Object value) {
        try {
            lock.lock();
            map.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    // 同步获取
    private Object getBySync(Map map, String key) {
        try {
            lock.lock();
            return map.get(key);
        } finally {
            lock.unlock();
        }
    }

    // 检查并清除过时数据
    public void checkAndCleanStaleData() {
        if (System.currentTimeMillis() - LastValueTime > overTime) {                     // 最后一次添加的数据已经超时
            this.clear();
        }else if(timeHashMap.size() > thresholdSize){                                    // 容量达到阈值时,需要检查
            List<String> keyList = new ArrayList<>(valueLinkedHashMap.keySet());
            int size = keyList.size(); String tempKey;
            for (int i = 0; i < size; i++) {
                tempKey = keyList.get(i);
                if (System.currentTimeMillis() - timeHashMap.get(tempKey) > overTime){   // 超时数据为僵尸数据,满足删除的条件
                    this.remove(tempKey);
                }else{
                    break;
                }
            }
        }
    }

    // 新增
    public void put(String key, Object value) {
        LastValueTime = System.currentTimeMillis();
        timeHashMap.put(key, LastValueTime);
        if (isSync){
            this.putBySync(valueLinkedHashMap, key, value);
        }else{
            valueLinkedHashMap.put(key, value);
        }
        this.checkAndCleanStaleData();                                                    // 检查数据是否过期
    }

    // 删除
    public Object remove(String key) {
        timeHashMap.remove(key);
        return valueLinkedHashMap.remove(key);
    }

    // 获取
    public Object get(String key) {
        this.checkAndCleanStaleData();
        return (timeHashMap.get(key) == null || System.currentTimeMillis() - timeHashMap.get(key) > overTime) ? null : (isSync ? this.getBySync(valueLinkedHashMap, key) : valueLinkedHashMap.get(key));
    }

    public Object getOrDefault(String key, Object defaultValue) {
        Object v = this.get(key);
        return v == null ? defaultValue : v;
    }

    // 判断是否存在key
    public boolean containsKey(String key) {
        return timeHashMap.containsValue(key);
    }

    // 判断是否存在value
    public boolean containsValue(Object value) {
        return valueLinkedHashMap.containsValue(value);
    }

    public void clear() {
        timeHashMap.clear();
        valueLinkedHashMap.clear();
    }

    public Set<String> keySet() {
        return valueLinkedHashMap.keySet();
    }

    public Collection<Object> values() {
        return valueLinkedHashMap.values();

    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return valueLinkedHashMap.entrySet();
    }

    public void forEach(BiConsumer<? super String, ? super Object> action) {
        valueLinkedHashMap.forEach(action);
    }

    public int getThresholdSize() {
        return thresholdSize;
    }

    public void setThresholdSize(int thresholdSize) {
        this.thresholdSize = thresholdSize;
    }

    public int getOverTime() {
        return overTime;
    }

    public void setOverTime(int overTime) {
        this.overTime = overTime;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    /**--------------------------------------------------------------------------------------*/
    private static class SingletonHolder {
        private static final SimpleTimeCache INSTANCE = new SimpleTimeCache();
    }
    public static SimpleTimeCache get(){
        return SingletonHolder.INSTANCE;
    }
    /**--------------------------------------------------------------------------------------*/


}