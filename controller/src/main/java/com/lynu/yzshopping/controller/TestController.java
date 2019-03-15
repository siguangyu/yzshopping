package com.lynu.yzshopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api("测试接口")
@RestController
public class TestController {

   /* @ApiOperation("传递对象数组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户列表",allowMultiple=true,dataType = "User",required = true)
    })
    @RequestMapping("getUser")
    public String getUser(@RequestBody List<User> user){
        String s = user.toString();
        System.out.println(s);
        ObjectMapper om=new ObjectMapper();
        *//*try {
            User user=om.readValue(userStr,User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*

        return "成功";
    }
    */
   @ApiOperation("传递字符串")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "name",value = "用户姓名",dataType = "string",required = true,paramType = "query")
   })
   @RequestMapping(value = "getName")
   public String getUser(HttpServletRequest request){
       String s = request.getParameter("name");
       System.out.println(s);

       return "成功";
   }

    @GetMapping("test")
    public String test(){
        return "helloworld";
    }
}
