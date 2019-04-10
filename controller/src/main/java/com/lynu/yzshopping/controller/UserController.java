package com.lynu.yzshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.mybatis.entity.Score;
import com.lynu.yzshopping.service.ScoreService;
import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.mybatis.entity.User;
import com.lynu.yzshopping.service.constants.YZConstants;
import com.lynu.yzshopping.util.Md5Util;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "用户接口", tags = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    ScoreService scoreService;


    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("register")
    public Result registerUser(@RequestBody String jsonBody) {
        try {
            //返回值为自增主键id
            int i = userService.insertSelective(jsonBody);
            if (i != 0) {
                User user = userService.selectByPrimaryKey(i);
                Map<String, Object> map = new HashMap<>();
                map.put("userId", i);
                map.put("userName", user.getUsername());
                map.put("account", user.getAccount());
                return ResultHandle.getSuccessResult("注册成功").setData(map);
            } else {
                return ResultHandle.getFailResult("注册失败");
            }
        } catch (Exception e) {
            return ResultHandle.getFailResult("系统错误");
        }
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("login")
    public Result loginUser(@RequestBody String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);
        String account = json.getString("account");
        String password = Md5Util.EncoderByMd5(json.getString("password"));
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        List<User> userList = userService.selectByConditionMap(map);
        if (userList.size() == 1) {
            User user = userList.get(0);
            Map<String, Object> backMap = new HashMap<>();
            backMap.put("userId", user.getId());
            backMap.put("userName", user.getUsername());
            return ResultHandle.getSuccessResult("登录成功").setData(backMap);
        } else {
            return ResultHandle.getFailResult("账户名或密码错误");
        }


    }

    /*
    * 查询用户信息
    * 使用场景：用户信息修改的时候进行回显
    * */
    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("selectByPrimaryKey")
    public Result selectByPrimaryKey(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);

        User user = userService.selectByPrimaryKey(id);
        Map<String, Object> userMap = new HashMap<>();
//        userMap.put("user", user);
        String birthday = user.getBirthday();
        String year = "";
        String month = "";
        String day = "";
        if (!StringUtils.isBlank(birthday)) {
            String[] split = birthday.split("-");
            year = split[0];
            month = split[1];
            day = split[2];
        }
        userMap.put("id", user.getId());
        userMap.put("account", user.getAccount());
        userMap.put("username", user.getUsername());
        userMap.put("phoneNumber", user.getPhoneNumber());
        userMap.put("qq", user.getQq());
        userMap.put("mail", user.getMail());
        userMap.put("year", year);
        userMap.put("month", month);
        userMap.put("day", day);

        return ResultHandle.getSuccessResult().setData(userMap);
    }


    @ApiOperation(value = "用户基本信息修改接口", notes = "用户基本信息修改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "birthday", value = "生日", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "qq", value = "QQ号码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "mail", value = "邮箱", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = false, dataType = "String", paramType = "query")
    })
    @PostMapping("updateUserInfo")
    public Result updateUserInfo(@RequestBody String jsonBody) {
        JSONObject json = JSONObject.parseObject(jsonBody);
        String id = (String) json.get("id");
        String account = (String) json.get("account");
        String oldPassword = Md5Util.EncoderByMd5((String) json.get("oldPassword"));
        if (!StringUtils.isBlank(account) && !StringUtils.isBlank(oldPassword)) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("account", account);
            map.put("password", oldPassword);
            List<User> userList = userService.selectByConditionMap(map);
            if (userList.size() != 1) {
                return ResultHandle.getFailResult("原密码错误");
            }
            String newPassword = Md5Util.EncoderByMd5((String) json.get("newPassword"));
            User user = new User();
            user.setId(Integer.parseInt(id));
            user.setAccount(account);
            user.setPassword(newPassword);
            user.setUpdateTime(new Date());
            String res = userService.updateByPrimaryKeySelective(user);
            if (res.contains("成功")) {
                return ResultHandle.getSuccessResult(res);
            } else {
                return ResultHandle.getFailResult(res);
            }
        } else {

            String username = (String) json.get("username");
//        String phoneNumber = request.getParameter("phoneNumber");
//        String birthday = (String) json.get("birthday");  //生日的格式  暂定为1996-12-12
            String year = (String) json.get("year");
            String month = (String) json.get("month");
            String day = (String) json.get("day");
            String birthday = null;
            if (!StringUtils.isBlank(year) && !StringUtils.isBlank(month) && !StringUtils.isBlank(day)) {
                birthday = year + "-" + month + "-" + day;
            }
            String qq = (String) json.get("qq");
            String mail = (String) json.get("mail");

            User user = new User();
            user.setId(Integer.parseInt(id));
            user.setUsername(username);
            user.setBirthday(birthday);
            user.setQq(qq);
//        user.setPhoneNumber(phoneNumber);
            user.setMail(mail);
            user.setUpdateTime(new Date());
            String res = userService.updateByPrimaryKeySelective(user);
            if (res.contains("成功")) {
                Map<String, Object> map = new HashMap<>();
                map.put("username", userService.selectByPrimaryKey(Integer.parseInt(id)).getUsername());
                return ResultHandle.getSuccessResult(res).setData(map);
            } else {
                return ResultHandle.getFailResult(res);
            }
        }
    }


    @ApiOperation(value = "安全信息修改接口", notes = "安全信息修改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldPassword", value = "原来的密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "query")

    })
    @PostMapping("updateUserSafeInfo")
    public Result updateUserSafeInfo(HttpServletRequest request) {

        String id = request.getParameter("id");
        String account = request.getParameter("account");
        String oldPassword = Md5Util.EncoderByMd5(request.getParameter("oldPassword"));

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("account", account);
        map.put("password", oldPassword);
        List<User> userList = userService.selectByConditionMap(map);
        if (userList.size() != 1) {
            return ResultHandle.getFailResult("原密码错误");
        }
        String newPassword = Md5Util.EncoderByMd5(request.getParameter("newPassword"));
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setAccount(account);
        user.setPassword(newPassword);
        user.setUpdateTime(new Date());
        String res = userService.updateByPrimaryKeySelective(user);
        if (res.contains("成功")) {
            return ResultHandle.getSuccessResult(res);
        } else {
            return ResultHandle.getFailResult(res);
        }
    }

    @ApiOperation(value = "用户签到接口", notes = "用户签到接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("signed")
    public Result signed(HttpServletRequest request) {
        //判断用户是否登录
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return ResultHandle.getFailResult("未登录");
        }
        //查询用户积分数量
        Map<String, Object> map = new HashMap<>();
        map.put("userId", Integer.parseInt(id));
        List<Score> scoreList = scoreService.selectByConditionMap(map);
        Score score = scoreList.get(scoreList.size() - 1);
        //获取用户操作积分的时间
        Date time1 = score.getCreateTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取今天0点的时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        String time2 = df.format(calendar.getTime());
        boolean before = true;
        try {
            before = time1.after(df.parse(time2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (before) {//如果数据库里的时间小于今天零点
            //则查询operation_describe是否为“签到”
            if ("签到".equals(score.getOperationDescribe())) {
                return ResultHandle.getFailResult("已签到");
            }
            //进行签到操作
            Integer scoreTotal = Integer.parseInt(score.getScoreTotal());
            scoreTotal += YZConstants.SIGNED_SCORE_NUMBER;
            score.setScoreTotal(scoreTotal + "");
            score.setId(score.getId() + 1);
            score.setCreateTime(new Date());
            score.setOperationNumber(YZConstants.SIGNED_SCORE_NUMBER + "");
            score.setOperationSign(YZConstants.PLUS_SIGN);
            score.setOperationDescribe("签到");

            //新增用户积分操作记录
            int i = scoreService.insert(score);

            if (i != 1) {
                return ResultHandle.getFailResult("签到失败");
            } else {
                return ResultHandle.getSuccessResult("签到成功");
            }
        } else {
            //如果数据库里的时间小于今天零点，证明今天未签到
            //进行签到操作

            Integer scoreTotal = Integer.parseInt(score.getScoreTotal());
            scoreTotal += YZConstants.SIGNED_SCORE_NUMBER;
            score.setScoreTotal(scoreTotal + "");
            score.setId(score.getId() + 1);
            score.setCreateTime(new Date());
            score.setOperationNumber(YZConstants.SIGNED_SCORE_NUMBER + "");
            score.setOperationSign(YZConstants.PLUS_SIGN);
            score.setOperationDescribe("签到");

            //新增用户积分操作记录
            int i = scoreService.insert(score);

            if (i != 1) {
                return ResultHandle.getFailResult("签到失败");
            } else {
                return ResultHandle.getSuccessResult("签到成功");
            }
        }

    }
        @ApiOperation(value = "初始化界面时判断是否签到", notes = "初始化界面时判断是否签到")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query")
        })
        @GetMapping("issigned")
        public Result isSigned (HttpServletRequest request){
            String id = request.getParameter("id");
            if (StringUtils.isBlank(id)) {
                return ResultHandle.getFailResult("未登录");
            }
            //查询用户积分数量
            Map<String, Object> map = new HashMap<>();
            map.put("userId", Integer.parseInt(id));
            List<Score> scoreList = scoreService.selectByConditionMap(map);
            Score score = scoreList.get(scoreList.size() - 1);
            //获取用户操作积分的时间
            Date time1 = score.getCreateTime();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取今天0点的时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            String time2 = df.format(calendar.getTime());
            boolean before = true;
            try {
                before = time1.after(df.parse(time2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (before) {//如果数据库里的时间小于今天零点
                //则查询operation_describe是否为“签到”
                if ("签到".equals(score.getOperationDescribe())) {
                    return ResultHandle.getFailResult("已签到");
                }
            }

            return ResultHandle.getSuccessResult("签到成功");
        }


}