package com.lynu.yzshopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: data-interface
 * @description:
 * @author: houyu
 * @create: 2018-12-06 16:39
 */
@RestController
// @RequestMapping("/hello")
public class HelloController {

  /*  @GetMapping()
    public String hello(){

        throw new RuntimeException("--------throw new RuntimeException()--------");

    }*/

    @RequestMapping("/hello")
    public String world(){
        return "hello world!";
    }

}
