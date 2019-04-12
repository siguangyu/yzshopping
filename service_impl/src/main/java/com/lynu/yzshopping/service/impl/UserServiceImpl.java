package com.lynu.yzshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.dao.UserDao;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    /*
    * 根据条件进行查询
    * */
    @Override
    public List<User> selectByConditionMap(Map<String, Object> map) {

        return userDao.selectByConditionMap(map);
    }

    @Override
    public int insertSelective(String jsonBody) {
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        User user=new User();
        String account=jsonObject.getString("account");
        if (StringUtils.isBlank(account)){
            return 0;
        }
        //使用Md5对密码进行加密处理
        String password= Md5Util.EncoderByMd5(jsonObject.getString("password"));
        if (StringUtils.isBlank(password)){
            return 0;
        }
        //根据时间戳来设置用户初始昵称
        String username=jsonObject.getString("username");
        if (username==null||username==""){
            long time = new Date().getTime();
            username=time+"_"+account;
        }
        user.setAccount(account);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUsername(username);
        userDao.insertSelective(user);
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateByPrimaryKeySelective(User user) {
        try{
            int i = userDao.updateByPrimaryKeySelective(user);
            if (i==1){
                return "修改成功";
            }
            return "修改出现错误";
        }catch (Exception e){
            return "修改出现错误";
        }
    }
}
