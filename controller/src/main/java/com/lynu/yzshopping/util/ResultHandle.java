package com.lynu.yzshopping.util;


import com.lynu.yzshopping.mybatis.entity.Result;

/**
 * @program: data-interface
 * @description: 结果处理类
 * @author: houyu
 * @create: 2018-12-10 09:00
 */
public class ResultHandle {

    //成功
    public static Result getSuccessResult() {
        return new Result().setCode(Result.Code.SUCCESS).setMessage("SUCCESS");
    }
    //成功
    public static Result getSuccessResult(String message) {
        return new Result().setCode(Result.Code.SUCCESS).setMessage(message);
    }

    //失败
    public static Result getFailResult() {
        return new Result().setCode(Result.Code.FAIL).setMessage("FAIL");
    }

    //失败
    public static Result getFailResult(String message) {
        return new Result().setCode(Result.Code.FAIL).setMessage(message);
    }

    //频繁
    public static Result getFrequentlyResult() {
        return new Result().setCode(Result.Code.FREQUENTLY).setMessage("FREQUENTLY：请求过太频繁,请稍后再试!");
    }

    //频繁
    public static Result getFrequentlyResult(String message) {
        return new Result().setCode(Result.Code.FREQUENTLY).setMessage(message);
    }

    //服务器内部错误
    public static Result getServerErrorResult() {
        return new Result().setCode(Result.Code.SERVER_ERROR).setMessage("SERVER_ERROR");
    }

    //服务器内部错误
    public static Result getServerErrorResult(String message) {
        return new Result().setCode(Result.Code.FREQUENTLY).setMessage(message);
    }

    //-------------------------------------------------------------------------------
}
